package com.MovieBookingSystem.MovieBookingSystem.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class TheatreRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theatreRoomId;
    @ManyToOne
    @JoinColumn(name="theatre_id")
    private Theatre theatre;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Seat> seats;

    @OneToMany(mappedBy = "room")
    private List<Show> shows;

    public Long getId() {
        return theatreRoomId;
    }

    public void setId(Long id) {
        this.theatreRoomId = id;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
}
