package com.MovieBookingSystem.MovieBookingSystem.Controller;

import com.MovieBookingSystem.MovieBookingSystem.Entity.User;
import com.MovieBookingSystem.MovieBookingSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authMgr;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) throws Exception {
        userService.registerUser(user);
        return "User Added";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) throws Exception {
        Authentication auth = authMgr.authenticate(new UsernamePasswordAuthenticationToken(user.getEmailId(),user.getPassword()));
        return "Login Successful";
    }
}
