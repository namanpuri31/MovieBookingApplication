package com.MovieBookingSystem.MovieBookingSystem.Controller;

import com.MovieBookingSystem.MovieBookingSystem.Entity.Movie;
import com.MovieBookingSystem.MovieBookingSystem.Entity.SeatAvailability;
import com.MovieBookingSystem.MovieBookingSystem.Entity.Show;
import com.MovieBookingSystem.MovieBookingSystem.Entity.Theatre;
import com.MovieBookingSystem.MovieBookingSystem.Service.*;
import com.MovieBookingSystem.MovieBookingSystem.Util.MovieDTO;
import com.MovieBookingSystem.MovieBookingSystem.Util.SeatAvailabilityDTO;
import com.MovieBookingSystem.MovieBookingSystem.Util.ShowDTO;
import com.MovieBookingSystem.MovieBookingSystem.Util.UpdateShowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    MovieService movieService;
    @Autowired
    TheatreService theatreService;
    @Autowired
    ShowService showService;
    @Autowired
    SeatBookingService seatBookingService;

    @Autowired
    private SeatAvailabilityService seatAvailabilityService;

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
    @PutMapping("/update-show")
    public String updateShow(@RequestBody UpdateShowDTO updateShowDTO){
        return showService.updateShow(updateShowDTO);
    }
    @DeleteMapping("/delete-show/{id}")
    public String deleteShow(@PathVariable Long id){
        return showService.deleteShow(id);
    }
    @GetMapping("/get-All-shows")
    public List<Show> getAllShows(){
        return showService.getAllShows();
    }

    @GetMapping("/get-show/{id}")
    public Show getShowById(@PathVariable Long id){
        return showService.getShowById(id);
    }

    @GetMapping("/seat-availability")
    public List<SeatAvailabilityDTO> getSeatAvailability(){
        List<SeatAvailability> availabilities= seatAvailabilityService.getSeatAvailability();
        return availabilities.stream().map(sa -> new SeatAvailabilityDTO(
                sa.getShow().getId(),
                sa.getSeat().getId(),
                sa.getStatus()
        )).collect(Collectors.toList());

    }

    @PostMapping("/seat-book/")
    public String BookSeat(@RequestBody SeatAvailabilityDTO seatData){
        return seatBookingService.bookSeat(seatData.getShowId(),seatData.getSeatId());
    }
}
