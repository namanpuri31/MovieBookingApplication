package com.MovieBookingSystem.MovieBookingSystem.Repository;

import com.MovieBookingSystem.MovieBookingSystem.Entity.Seat;
import com.MovieBookingSystem.MovieBookingSystem.Entity.TheatreRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepo extends JpaRepository<Seat,Long> {
    List<Seat> findByRoom(TheatreRoom th);
}
