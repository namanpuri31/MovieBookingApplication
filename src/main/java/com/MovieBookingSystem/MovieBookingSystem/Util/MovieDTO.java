package com.MovieBookingSystem.MovieBookingSystem.Util;

import java.util.List;

public class MovieDTO {
    String movieName;
    Integer duration;
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    //    List<Long> theatres;
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

//    public List<Long> getTheatres() {
//        return theatres;
//    }
//
//    public void setTheatres(List<Long> theatres) {
//        this.theatres = theatres;
//    }
}
