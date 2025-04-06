package com.MovieBookingSystem.MovieBookingSystem.Entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String emailId;
    private String password;

    public String getEmailId() {

        return emailId;

    }

    public void setEmailId(String email_id) {

        this.emailId = email_id;

    }

    public String getPassword() {

        return password;

    }

    public void setPassword(String password) {

        this.password = password;

    }

}

