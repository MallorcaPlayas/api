package org.example.apirest.controller;

import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.service.user.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController extends GeneralizedControllerImpl<UserDto, CreateUserDto> {
    public UserController(UserServiceImpl service) {
        super(service);
    }
}
