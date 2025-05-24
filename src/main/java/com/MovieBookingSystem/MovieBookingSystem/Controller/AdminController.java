package com.MovieBookingSystem.MovieBookingSystem.Controller;

import com.MovieBookingSystem.MovieBookingSystem.Entity.Movie;
import com.MovieBookingSystem.MovieBookingSystem.Entity.Theatre;
import com.MovieBookingSystem.MovieBookingSystem.Service.MovieService;
import com.MovieBookingSystem.MovieBookingSystem.Service.ShowService;
import com.MovieBookingSystem.MovieBookingSystem.Service.TheatreService;
import com.MovieBookingSystem.MovieBookingSystem.Util.MovieDTO;
import com.MovieBookingSystem.MovieBookingSystem.Util.ShowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    MovieService movieService;
    @Autowired
    TheatreService theatreService;
    @Autowired
    ShowService showService;

    @PostMapping("/save-movie")
    public String saveMovie(@RequestBody MovieDTO movie){
        movieService.saveMovie(movie);
        return "Movie Added!!";
    }
    @DeleteMapping("/delete-movie/{id}")
    public String deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return "Movie deleted";
    }
    @GetMapping("/findby-movieid/{id}")
    public String findMovieById(@PathVariable Long id){

        return movieService.getMovieById(id).getMovieName();
    }
    @GetMapping("/findall-movie")
    public List<Movie> findAllMovies(){
        return movieService.getAllMovies();
    }
    // Theatre CRUD

    @PostMapping("/save-theatre")
    public String saveTheatre(@RequestBody Theatre th){
        theatreService.addTheater(th);
        return "Theatre added";
    }
    @DeleteMapping("/delete-theatre/{id}")
    public String deleteTheatre(@PathVariable Long id){
        theatreService.removeTheatre(id);
        return "Theatre deleted";
    }
    @GetMapping("findby-theatreid/{id}")
    public Theatre findTheatreById(@PathVariable Long id){
        return theatreService.getTheatre(id);
    }
    @GetMapping("/findall-theatre")
    public List<Theatre> findAllTheatre(){
        return theatreService.getAllTheatre();
    }
    @PostMapping("/save-show")
    public String saveShow(@RequestBody ShowDTO show){
        showService.addShow(show);
        return "Show added";
    }
}
