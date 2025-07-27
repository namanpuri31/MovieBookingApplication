package com.MovieBookingSystem.MovieBookingSystem.Repository;
import com.MovieBookingSystem.MovieBookingSystem.Entity.TheatreRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRoomRepo extends JpaRepository<TheatreRoom, Long> {

}
