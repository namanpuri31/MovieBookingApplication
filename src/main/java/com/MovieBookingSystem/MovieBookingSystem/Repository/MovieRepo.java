package com.MovieBookingSystem.MovieBookingSystem.Repository;

import com.MovieBookingSystem.MovieBookingSystem.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Long> {
    Movie findByMovieName(String movieName);
}
