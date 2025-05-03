package com.MovieBookingSystem.MovieBookingSystem.Service;

import com.MovieBookingSystem.MovieBookingSystem.Entity.Theatre;
import com.MovieBookingSystem.MovieBookingSystem.Repository.TheatreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreService {
    @Autowired
    TheatreRepo theatreRepo;
    public void addTheater(Theatre th){
        theatreRepo.save(th);
        return;
    }
    public void removeTheatre(Long id){
        theatreRepo.deleteById(id);
        return;
    }
    public Theatre getTheatre(Long id){
        return theatreRepo.findById(id).orElseThrow(()->new RuntimeException("Cant find a Theatre by the provided Theatre ID"));
    }
    public List<Theatre> getAllTheatre(){
        return theatreRepo.findAll();
    }
}
