package com.MovieBookingSystem.MovieBookingSystem.Service;

import com.MovieBookingSystem.MovieBookingSystem.Entity.SeatAvailability;
import com.MovieBookingSystem.MovieBookingSystem.Repository.SeatAvailabilityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatAvailabilityService {
    @Autowired
    SeatAvailabilityRepo seatAvailabilityRepo;

    public List<SeatAvailability> getSeatAvailability(){
        return seatAvailabilityRepo.findAll();
    }
}
