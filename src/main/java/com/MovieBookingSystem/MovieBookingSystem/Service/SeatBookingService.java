package com.MovieBookingSystem.MovieBookingSystem.Service;

import com.MovieBookingSystem.MovieBookingSystem.Entity.Payment;
import com.MovieBookingSystem.MovieBookingSystem.Entity.PaymentStatus;
import com.MovieBookingSystem.MovieBookingSystem.Entity.SeatAvailability;
import com.MovieBookingSystem.MovieBookingSystem.Repository.PaymentRepo;
import com.MovieBookingSystem.MovieBookingSystem.Repository.SeatAvailabilityRepo;
import com.razorpay.RazorpayException;
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

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private SeatAvailabilityRepo seatAvailabilityRepository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ShowService showService;

    @Autowired
    private PaymentRepo paymentRepository;

    @Autowired
    private PaymentGatewayService paymentGatewayService;
    public String bookSeat(Long showId, Long seatId,String userId) {
        String lockKey = "lock:seat:" + showId + ":" + seatId;
        RLock lock = redissonClient.getLock(lockKey);

        try {
            boolean locked = lock.tryLock(5, 40, TimeUnit.SECONDS); // wait 5s max, hold for 10s
            if (!locked) {
                throw new RuntimeException("Seat is currently being booked by someone else.");
            }

            SeatAvailability sa = seatAvailabilityRepository.findByShowIdAndSeatId(showId, seatId);
            if (!"AVAILABLE".equals(sa.getStatus())) {
                return "Seat is already booked";
            }

            sa.setStatus("RESERVED");
            seatAvailabilityRepository.save(sa);

            double amount=showService.getShowById(showId).getAmount();
            // Step 2: Create Payment Order with Razorpay
            String paymentOrderId = paymentGatewayService.createPaymentOrder(amount);
            Payment payment = new Payment();
            payment.setPaymentId(paymentOrderId);
            payment.setUserId(userId);
            payment.setShowId(showId);
            payment.setSeatId(seatId);
            payment.setAmount(amount);
            payment.setStatus(PaymentStatus.INITIATED);
            payment.setGateway("RAZORPAY");
            payment.setCreatedAt(LocalDateTime.now());
            paymentRepository.save(payment);
            confirmPayment(paymentOrderId,true);

            return paymentOrderId;

            // Optionally update cache here
        } catch (Exception e) {
            throw new RuntimeException("Interrupted while trying to lock seat", e);
        }finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
    @Transactional
    public void confirmPayment(String paymentId, boolean isSuccess) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        SeatAvailability sa = seatAvailabilityRepository.findByShowIdAndSeatId(payment.getShowId(), payment.getSeatId());

        if (isSuccess) {
            payment.setStatus(PaymentStatus.SUCCESS);
            sa.setStatus("BOOKED");
        } else {
            payment.setStatus(PaymentStatus.FAILURE);
            sa.setStatus("AVAILABLE"); // Release seat
        }

        paymentRepository.save(payment);
        seatAvailabilityRepository.save(sa);

        String redisKey = "seat_availability_cache";
        String redisField = payment.getShowId() + ":" + payment.getSeatId();
        redisTemplate.opsForHash().put(redisKey, redisField, sa.getStatus());
    }

}
