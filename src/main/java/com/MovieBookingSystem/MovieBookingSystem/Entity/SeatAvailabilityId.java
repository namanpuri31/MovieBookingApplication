package com.MovieBookingSystem.MovieBookingSystem.Entity;


import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SeatAvailabilityId implements Serializable {

    private Long showId;
    private Long seatId;

    public SeatAvailabilityId() {}

    public SeatAvailabilityId(Long showId, Long seatId) {
        this.showId = showId;
        this.seatId = seatId;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeatAvailabilityId)) return false;
        SeatAvailabilityId that = (SeatAvailabilityId) o;
        return Objects.equals(showId, that.showId) &&
                Objects.equals(seatId, that.seatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(showId, seatId);
    }
}



