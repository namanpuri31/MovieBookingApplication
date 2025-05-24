package com.MovieBookingSystem.MovieBookingSystem.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private TheatreRoom room;

    public Long getId() {
        return showId;
    }

    public void setId(Long id) {
        this.showId = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public TheatreRoom getRoom() {
        return room;
    }

    public void setRoom(TheatreRoom room) {
        this.room = room;
    }
}
