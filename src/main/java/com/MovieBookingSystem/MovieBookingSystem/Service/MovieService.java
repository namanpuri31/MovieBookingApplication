package com.MovieBookingSystem.MovieBookingSystem.Service;

import com.MovieBookingSystem.MovieBookingSystem.Entity.Movie;
import com.MovieBookingSystem.MovieBookingSystem.Repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepo movieRepo;

    public void saveMovie(Movie movie){// it does both update and add
        movieRepo.save(movie);
    }
    public void deleteMovie(Long id){
        movieRepo.deleteById(id);
    }
    public Movie getMovieById(Long id){
        return movieRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }
    public List<Movie> getAllMovies(){
        return movieRepo.findAll();
    }
}
