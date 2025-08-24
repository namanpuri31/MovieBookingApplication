package com.MovieBookingSystem.MovieBookingSystem.Service;

import com.MovieBookingSystem.MovieBookingSystem.Entity.Payment;
import com.MovieBookingSystem.MovieBookingSystem.Entity.PaymentStatus;
import com.MovieBookingSystem.MovieBookingSystem.Entity.Seat;
import com.MovieBookingSystem.MovieBookingSystem.Entity.SeatAvailability;
import com.MovieBookingSystem.MovieBookingSystem.Repository.PaymentRepo;
import com.MovieBookingSystem.MovieBookingSystem.Repository.SeatAvailabilityRepo;
import com.MovieBookingSystem.MovieBookingSystem.Repository.SeatRepo;
import com.stripe.exception.StripeException;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;


@Service
public class SeatBookingService {
    @Autowired private RedissonClient redissonClient;
    @Autowired private RedisTemplate<String, Object> redisTemplate;
    @Autowired private PaymentRepo paymentRepository;
    @Autowired private ShowService showService;
    @Autowired private SeatAvailabilityRepo seatAvailabilityRepo;
    @Autowired private PaymentGatewayService paymentGatewayService;
    public String reserveSeatAndPay(Long showId, Long seatId, String userId) {
        String lockKey = "lock:seat:" + showId + ":" + seatId;
        RLock lock = redissonClient.getLock(lockKey);
        System.out.println("Entered Seat Booking class");
        try {
            System.out.println("Entered Try block");
            boolean locked = lock.tryLock(5, 10, TimeUnit.SECONDS);
            if (!locked) {
                throw new RuntimeException("Seat is currently being reserved by someone else.");
            }
            String redisKey = "seat_availability_cache";
            String redisField = showId + ":" + seatId;
            String currentStatus = (String) redisTemplate.opsForHash().get(redisKey, redisField);

            if ("RESERVED".equals(currentStatus)) {
                return "Seat is already temporarily reserved.";
            }

            // Mark in Redis for 2 minutes
            redisTemplate.opsForHash().put(redisKey, redisField, "RESERVED");

            // Create payment intent
            double amount = showService.getShowById(showId).getAmount();
            String paymentOrderId = paymentGatewayService.createPaymentIntent(amount);

            Payment payment = new Payment();
            payment.setPaymentId(paymentOrderId);
            payment.setUserId(userId);
            payment.setShowId(showId);
            payment.setSeatId(seatId);
            payment.setAmount(amount);
            payment.setStatus(PaymentStatus.INITIATED);
            payment.setGateway("Stripe");
            payment.setCreatedAt(LocalDateTime.now());
            paymentRepository.save(payment);

            // 🚨 Auto-confirm here (for testing only)
            boolean mockSuccess = true; // or randomize for failure simulation
            System.out.println("PaymentOrderID : " + paymentOrderId);
            confirmPayment(paymentOrderId, mockSuccess);

            return paymentOrderId;

        } catch (Exception e) {
            throw new RuntimeException("Error while reserving seat", e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
    @Transactional
    public void confirmPayment(String paymentId, boolean isSuccess) {
        Payment payment = paymentRepository.findById(paymentId) .orElseThrow(() -> new RuntimeException("Payment not found"));
        String redisKey = "seat_availability_cache";
        String redisField = payment.getShowId() + ":" + payment.getSeatId();
        if (isSuccess) {
            payment.setStatus(PaymentStatus.SUCCESS);
            // DB booking logic
            SeatAvailability seatAvailability = seatAvailabilityRepo.findByShowIdAndSeatId(payment.getShowId(), payment.getSeatId());
            seatAvailability.setStatus("BOOKED");
            seatAvailabilityRepo.save(seatAvailability);
            redisTemplate.opsForHash().put(redisKey, redisField, "BOOKED");
        }
        else {
            payment.setStatus(PaymentStatus.FAILURE); // Let Redis TTL expire → frees seat
            redisTemplate.opsForHash().put(redisKey, redisField, "AVAILABLE");
        }
        paymentRepository.save(payment);

    }
}

