package com.MovieBookingSystem.MovieBookingSystem.Util;

public class SeatAvailabilityDTO {
    private Long showId;
    private Long seatId;
    private String status;

    public SeatAvailabilityDTO(Long showId, Long seatId, String status) {
        this.showId = showId;
        this.seatId = seatId;
        this.status = status;
    }

    // Getters and Setters
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
