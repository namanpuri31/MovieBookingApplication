package com.MovieBookingSystem.MovieBookingSystem.Entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class SeatAvailability {
    @EmbeddedId
    private SeatAvailabilityId id;

    @ManyToOne
    @MapsId("showId")
    private Show show;

    @ManyToOne
    @MapsId("seatId")
    private Seat seat;

    private String status;

    public SeatAvailabilityId getId() {
        return id;
    }

    public void setId(SeatAvailabilityId id) {
        this.id = id;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
