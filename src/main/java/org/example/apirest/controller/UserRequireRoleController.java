package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.userRequireRole.CreateUserRequireRoleDto;
import org.example.apirest.dto.userRequireRole.UserRequireRoleDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.userRequireRole.UserRequireRoleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user-require-role")
@CrossOrigin(origins = "*")
public class UserRequireRoleController extends GeneralizedController<UserRequireRoleDto, CreateUserRequireRoleDto> {
    public UserRequireRoleController(UserRequireRoleServiceImpl  service) {
        super(service);
    }
}
