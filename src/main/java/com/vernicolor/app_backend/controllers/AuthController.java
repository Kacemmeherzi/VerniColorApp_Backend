package com.vernicolor.app_backend.controllers;


import com.vernicolor.app_backend.dto.LoginDTO;
import com.vernicolor.app_backend.dto.LoginResponse;
import com.vernicolor.app_backend.models.User;
import com.vernicolor.app_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO logindto) {
        if (userService.validateUser(logindto.getUsername(), logindto.getPassword()) ) {
            User user = userService.findByUsername(logindto.getUsername());
            LoginResponse loginResponse = new LoginResponse("connected",user) ;
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }
}
