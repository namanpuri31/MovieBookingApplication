package com.MovieBookingSystem.MovieBookingSystem.Service;

import com.MovieBookingSystem.MovieBookingSystem.Entity.SeatAvailability;
import com.MovieBookingSystem.MovieBookingSystem.Repository.SeatAvailabilityRepo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class SeatBookingService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private SeatAvailabilityRepo seatAvailabilityRepository;

    public void bookSeat(Long showId, Long seatId) {
        String lockKey = "lock:seat:" + showId + ":" + seatId;
        RLock lock = redissonClient.getLock(lockKey);

        try {
            boolean locked = lock.tryLock(3, 2, TimeUnit.MINUTES); // wait 5s max, hold for 10s
            if (!locked) {
                throw new RuntimeException("Seat is currently being booked by someone else.");
            }

            SeatAvailability sa = seatAvailabilityRepository.findByShowIdAndSeatId(showId, seatId);
            if (!"AVAILABLE".equals(sa.getStatus())) {
                throw new RuntimeException("Seat already booked.");
            }

            sa.setStatus("BOOKED");
            seatAvailabilityRepository.save(sa);

            // Optionally update cache here
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while trying to lock seat", e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
