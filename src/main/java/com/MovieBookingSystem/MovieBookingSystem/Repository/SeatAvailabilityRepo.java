package com.MovieBookingSystem.MovieBookingSystem.Repository;

import com.MovieBookingSystem.MovieBookingSystem.Entity.SeatAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatAvailabilityRepo extends JpaRepository<SeatAvailability,Long> {
}
