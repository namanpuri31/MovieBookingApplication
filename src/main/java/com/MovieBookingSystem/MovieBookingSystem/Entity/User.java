package com.MovieBookingSystem.MovieBookingSystem.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String emailId;
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

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

