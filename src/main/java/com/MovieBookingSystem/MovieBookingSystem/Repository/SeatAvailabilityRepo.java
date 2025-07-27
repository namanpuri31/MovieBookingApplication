package com.MovieBookingSystem.MovieBookingSystem.Repository;

import com.MovieBookingSystem.MovieBookingSystem.Entity.SeatAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatAvailabilityRepo extends JpaRepository<SeatAvailability,Long> {
    SeatAvailability findByShowIdAndSeatId(Long showId, Long seatId);
}
