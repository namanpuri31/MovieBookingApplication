package com.MovieBookingSystem.MovieBookingSystem.Controller;

import com.MovieBookingSystem.MovieBookingSystem.Entity.Role;
import com.MovieBookingSystem.MovieBookingSystem.Entity.User;
import com.MovieBookingSystem.MovieBookingSystem.Repository.RoleRepo;
import com.MovieBookingSystem.MovieBookingSystem.Service.CustomUserDetailsService;
import com.MovieBookingSystem.MovieBookingSystem.Service.UserService;
import com.MovieBookingSystem.MovieBookingSystem.Util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authMgr;
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    RoleRepo roleRepo;
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) throws Exception {
        Role role=roleRepo.findByRoleName("ROLE_USER");
        user.setRole(role);
        userService.registerUser(user);
        return "User Added";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) throws Exception {
        try {
            Authentication auth = authMgr.authenticate(new UsernamePasswordAuthenticationToken(user.getEmailId(), user.getPassword()));
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmailId());
            String role = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst() // Get the first (and only) role
                    .orElseThrow(() -> new Exception("No role assigned to the user"));

            String token = jwtUtil.generateToken(user.getEmailId(), role);
            return token;
        }
        catch (UsernameNotFoundException e) {
            return "User not found. Please register first.";
        }
    }
}
