package com.MovieBookingSystem.MovieBookingSystem.Controller;

import com.MovieBookingSystem.MovieBookingSystem.Entity.Movie;
import com.MovieBookingSystem.MovieBookingSystem.Repository.MovieRepo;
import com.MovieBookingSystem.MovieBookingSystem.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    MovieService movieService;
    @PostMapping("/save-movie")
    public String saveMovie(@RequestBody Movie movie){
        movieService.saveMovie(movie);
        return "Movie Added!!";
    }
    @PostMapping("/update-movie")
    public String updateMovie(@RequestBody Movie movie){
        movieService.saveMovie(movie);
        return "Movie updated!!";
    }
    @DeleteMapping("/delete-movie/{id}")
    public String deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return "Movie deleted";
    }
    @GetMapping("/find-by-movieid/{id}")
    public String findMovieById(@PathVariable Long id){
       return movieService.getMovieById(id).getMovieName();
    }
    @GetMapping("/findall-movie")
    public List<Movie> findAllMovies(){
        return movieService.getAllMovies();
    }
}
