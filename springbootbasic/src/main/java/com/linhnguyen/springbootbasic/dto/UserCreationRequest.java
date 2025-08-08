package com.linhnguyen.springbootbasic.dto;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserCreationRequest {
    @Size(min = 3, message = "Password must be at laest 3 characters")
    private String username;
    @Size(min = 8, message = "Password must be at laest 8 characters")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
