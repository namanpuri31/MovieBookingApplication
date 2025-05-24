package com.MovieBookingSystem.MovieBookingSystem.Util;

import com.MovieBookingSystem.MovieBookingSystem.Entity.Movie;
import com.MovieBookingSystem.MovieBookingSystem.Entity.TheatreRoom;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class ShowDTO {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;

    private Long movie;

    private Long room;

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

    public Long getMovie() {
        return movie;
    }

    public void setMovie(Long movie) {
        this.movie = movie;
    }

    public Long getRoom() {
        return room;
    }

    public void setRoom(Long room) {
        this.room = room;
    }


}
