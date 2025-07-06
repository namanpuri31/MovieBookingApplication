package com.MovieBookingSystem.MovieBookingSystem.Service;

import com.MovieBookingSystem.MovieBookingSystem.Entity.SeatAvailability;
import com.MovieBookingSystem.MovieBookingSystem.Entity.SeatAvailabilityId;
import com.MovieBookingSystem.MovieBookingSystem.Repository.SeatAvailabilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SeatAvailabilityService {
    @Autowired
    SeatAvailabilityRepo seatAvailabilityRepo;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String REDIS_KEY = "seat_availability_cache";
    public List<SeatAvailability> getSeatAvailability() {
        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();

        // ✅ Step 1: Check if cache already exists
        if (redisTemplate.hasKey(REDIS_KEY)) {
            System.out.println("⚡ Serving all seat availability from Redis cache");

            Map<Object, Object> cachedMap = hashOps.entries(REDIS_KEY);

            return cachedMap.entrySet().stream()
                    .map(entry -> {
                        String[] parts = entry.getKey().toString().split(":");
                        SeatAvailability seat = new SeatAvailability();
                        SeatAvailabilityId seatAvailabilityId = new SeatAvailabilityId();
                        seatAvailabilityId.setSeatId(Long.parseLong(parts[1]));
                        seatAvailabilityId.setShowId(Long.parseLong(parts[0]));
                        seat.setId(seatAvailabilityId);
                        seat.setStatus(entry.getValue().toString());
                        return seat;
                    }).collect(Collectors.toList());
        }
        // ❌ Cache miss → fetch from DB
        System.out.println("🐢 Cache miss: Fetching all seat availability from DB");
        List<SeatAvailability> allSeats = seatAvailabilityRepo.findAll();

        // ✅ Step 2: Store each record in Redis hash
        for (SeatAvailability seat : allSeats) {
            String fieldKey = seat.getShow().getId()+ ":" + seat.getSeat().getId();
            hashOps.put(REDIS_KEY, fieldKey, seat.getStatus());
        }
        return allSeats;
    }

}
