package com.MovieBookingSystem.MovieBookingSystem.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Seat {
    @Id
    @GeneratedValue
    private Long seatId;

    private String seatNumber; // e.g., A1, B2

    @ManyToOne
    private TheatreRoom room;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeatAvailability> seatAvailabilities;

    public Long getId() {
        return seatId;
    }

    public void setId(Long id) {
        this.seatId = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public TheatreRoom getRoom() {
        return room;
    }

    public void setRoom(TheatreRoom room) {
        this.room = room;
    }
}
