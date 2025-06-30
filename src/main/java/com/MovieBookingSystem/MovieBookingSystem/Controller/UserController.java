package com.MovieBookingSystem.MovieBookingSystem.Controller;
import com.MovieBookingSystem.MovieBookingSystem.Entity.SeatAvailability;
import com.MovieBookingSystem.MovieBookingSystem.Service.SeatAvailabilityService;
import com.MovieBookingSystem.MovieBookingSystem.Service.SeatBookingService;
import com.MovieBookingSystem.MovieBookingSystem.Util.SeatAvailabilityDTO;
import org.springframework.web.bind.annotation.*;
import com.MovieBookingSystem.MovieBookingSystem.Entity.User;
import com.MovieBookingSystem.MovieBookingSystem.Service.UserService;
import com.MovieBookingSystem.MovieBookingSystem.Util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SeatAvailabilityService seatAvailabilityService;

    @Autowired
    private SeatBookingService seatBookingService;

    @GetMapping("/")
    public String hello() {
        return "hello user !!!!";
    }

    @GetMapping("/seat-availability")
    public List<SeatAvailability> getSeatAvailability(){
        return seatAvailabilityService.getSeatAvailability();
    }

    @PostMapping("/seat-book/")
    public String BookSeat(@RequestBody SeatAvailabilityDTO seatData){
        return seatBookingService.bookSeat(seatData.getShowId(),seatData.getSeatId());
    }
}
