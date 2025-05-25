package com.MovieBookingSystem.MovieBookingSystem.Service;

import com.MovieBookingSystem.MovieBookingSystem.Entity.*;
import com.MovieBookingSystem.MovieBookingSystem.Repository.*;
import com.MovieBookingSystem.MovieBookingSystem.Util.ShowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

@Service
public class ShowService {
    @Autowired
    MovieRepo movieRepo;

    @Autowired
    TheatreRoomRepo theatreRoomRepo;

    @Autowired
    SeatRepo seatRepo;

    @Autowired
    SeatAvailabilityRepo seatAvailabilityRepo;

    @Autowired
    ShowRepo showRepo;

    @Transactional
    public void addShow(ShowDTO showDto){
        Show s=new Show();
        s.setStartTime(showDto.getStartTime());
        s.setEndTime(showDto.getEndTime());

        Movie movie=movieRepo.findById(showDto.getMovie())
                .orElseThrow(()->new RuntimeException("Cant find the movie with the provided ID"));
        TheatreRoom theatreRoom=theatreRoomRepo.findById(showDto.getRoom())
                .orElseThrow(()->new RuntimeException("Cant find the theatre room with the provided ID"));
        s.setRoom(theatreRoom);
        s.setMovie(movie);

        Show show = showRepo.save(s);

        List<Seat> seats = seatRepo.findByRoom(theatreRoom);
        List<SeatAvailability> availabilityList = new ArrayList<>();

        for (Seat seat : seats) {
            SeatAvailability sa = new SeatAvailability();
            sa.setShow(show);
            sa.setSeat(seat);
            sa.setStatus("AVAILABLE");
            availabilityList.add(sa);
        }

        seatAvailabilityRepo.saveAll(availabilityList);
    }
}
