package com.vernicolor.app_backend.services;


import com.vernicolor.app_backend.models.User;
import com.vernicolor.app_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User findByUsername(String user) {

        return userRepository.findByUsername(user);
    }



    public  boolean validateUser(String username, String password) {
        User user = findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
    public User addUser(User user) {
        return userRepository.save(user);
    }
}
