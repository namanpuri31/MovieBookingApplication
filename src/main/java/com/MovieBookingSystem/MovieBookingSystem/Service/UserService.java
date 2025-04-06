package com.MovieBookingSystem.MovieBookingSystem.Service;

import com.MovieBookingSystem.MovieBookingSystem.Entity.User;
import com.MovieBookingSystem.MovieBookingSystem.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void registerUser(User user) throws Exception {
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
            return;
        }
        catch(Exception exp){
            throw new Exception(exp);
        }
    }
}
