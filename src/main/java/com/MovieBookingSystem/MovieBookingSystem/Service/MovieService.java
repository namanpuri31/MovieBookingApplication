package com.MovieBookingSystem.MovieBookingSystem.Service;

import com.MovieBookingSystem.MovieBookingSystem.Entity.Movie;
import com.MovieBookingSystem.MovieBookingSystem.Entity.Theatre;
import com.MovieBookingSystem.MovieBookingSystem.Repository.MovieRepo;
import com.MovieBookingSystem.MovieBookingSystem.Repository.TheatreRepo;
import com.MovieBookingSystem.MovieBookingSystem.Util.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieService {
    @Autowired
    MovieRepo movieRepo;
    @Autowired
    TheatreRepo theatreRepo;

    public void saveMovie(MovieDTO dto){// it does both update and add
        Movie movie=new Movie();
        movie.setMovieName(dto.getMovieName());
        Set<Theatre> theatreSet=new HashSet<>();
        //movie -> theatre

        for(Long id : dto.getTheatres()){
            Theatre th = theatreRepo.findById(id).orElseThrow(()->new RuntimeException("Cant find theatre with id "+id));
            theatreSet.add(th);
        }
        for(Theatre th : theatreSet){
            th.getMovies().add(movie);
        }
        movie.setTheatres(theatreSet);
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
