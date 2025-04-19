package com.MovieBookingSystem.MovieBookingSystem.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.MovieBookingSystem.MovieBookingSystem.Entity.User;
import com.MovieBookingSystem.MovieBookingSystem.Service.UserService;
import com.MovieBookingSystem.MovieBookingSystem.Util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/")
    public String hello() {
        return "hello user !!!!";
    }

}
