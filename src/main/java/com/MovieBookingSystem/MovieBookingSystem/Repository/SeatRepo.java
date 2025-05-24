package com.MovieBookingSystem.MovieBookingSystem.Repository;

import com.MovieBookingSystem.MovieBookingSystem.Entity.Seat;
import com.MovieBookingSystem.MovieBookingSystem.Entity.TheatreRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SeatRepo extends JpaRepository<Seat,Long> {
    List<Seat> findByTheatreRoom(TheatreRoom th);
}
