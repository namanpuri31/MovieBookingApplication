package com.MovieBookingSystem.MovieBookingSystem.Util;

public class SeatBookingDTO {
    private Long showId;
    private Long seatId;
    private String status;

    private String userId;

    public SeatBookingDTO(Long showId, Long seatId, String status, String userId) {
        this.showId = showId;
        this.seatId = seatId;
        this.status = status;
        this.userId = userId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
