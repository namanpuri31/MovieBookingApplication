package com.MovieBookingSystem.MovieBookingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MovieBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieBookingSystemApplication.class, args);
	}

}
