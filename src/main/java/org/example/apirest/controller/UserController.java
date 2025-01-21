package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.user.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController extends GeneralizedController<UserDto, CreateUserDto> {
    public UserController(UserServiceImpl service) {
        super(service);
    }
}
