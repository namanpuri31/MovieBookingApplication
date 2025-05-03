package com.MovieBookingSystem.MovieBookingSystem.Util;

import java.util.List;

public class MovieDTO {
    String movieName;
    List<Long> theatres;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public List<Long> getTheatres() {
        return theatres;
    }

    public void setTheatres(List<Long> theatres) {
        this.theatres = theatres;
    }
}
