package org.example.apirest.controller;

import org.example.apirest.model.UserMongoDB;
import org.example.apirest.repository.UserRepositoryMongoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserControllerMongoDB {
    @Autowired
    private UserRepositoryMongoDB userRepository;

    @GetMapping("/usersMongoDB")
    public List<UserMongoDB> getAllUsers() {
        return userRepository.findAll();
    }
}
