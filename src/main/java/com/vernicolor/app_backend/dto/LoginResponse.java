package com.vernicolor.app_backend.dto;


import com.vernicolor.app_backend.models.User;

public class LoginResponse {
    private String message ;
    private User user ;
    public LoginResponse(String message, User user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
