package com.MovieBookingSystem.MovieBookingSystem.Service;

import com.MovieBookingSystem.MovieBookingSystem.Entity.*;
import com.MovieBookingSystem.MovieBookingSystem.Repository.*;
import com.MovieBookingSystem.MovieBookingSystem.Util.ShowDTO;
import com.MovieBookingSystem.MovieBookingSystem.Util.UpdateShowDTO;
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


        Movie movie=movieRepo.findById(showDto.getMovie())
                .orElseThrow(()->new RuntimeException("Cant find the movie with the provided ID"));

        TheatreRoom theatreRoom=theatreRoomRepo.findById(showDto.getRoom())
                .orElseThrow(()->new RuntimeException("Cant find the theatre room with the provided ID"));
        s.setEndTime(showDto.getStartTime().plusMinutes(movie.getDurationInMinutes()));
        s.setRoom(theatreRoom);
        s.setMovie(movie);
        s.setAmount(showDto.getAmount());

        Show show = showRepo.save(s);

        List<Seat> seats = seatRepo.findByRoom(theatreRoom);
        List<SeatAvailability> availabilityList = new ArrayList<>();

        for (Seat seat : seats) {
            SeatAvailability sa = new SeatAvailability();
            sa.setShow(show);
            sa.setSeat(seat);
            sa.setStatus("AVAILABLE");


            SeatAvailabilityId id = new SeatAvailabilityId();
            id.setShowId(show.getId());
            id.setSeatId(seat.getId());
            sa.setId(id);

            availabilityList.add(sa);
        }

        seatAvailabilityRepo.saveAll(availabilityList);
    }

    public String deleteShow(Long showId){
        showRepo.deleteById(showId);
        return "show deleted successfully of showId: "+showId;
    }

    @Transactional
    public String updateShow(UpdateShowDTO show){
        Show curr =showRepo.findById(show.getShowDTOid()).orElseThrow(()->new RuntimeException("Cant find the show with the provided ID"));
        curr.setEndTime(show.getEndTime());
        curr.setStartTime(show.getStartTime());
        curr.setRoom(theatreRoomRepo.findById(show.getRoom()).orElseThrow(()->new RuntimeException("Cant find the room with the provided ID")));
        curr.setMovie(movieRepo.findById(show.getMovie()).orElseThrow(()->new RuntimeException("Cant find the movie with the provided ID")));
        showRepo.save(curr);
        return "show is updated";
    }
    public List<Show> getAllShows(){
        return showRepo.findAll();
    }
    public Show getShowById(Long id){
         return showRepo.findById(id).orElseThrow(()->new RuntimeException("Cant find the show with the provided ID"));
    }
}
