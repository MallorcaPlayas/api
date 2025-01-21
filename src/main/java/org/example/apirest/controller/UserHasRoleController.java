package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.userHasRole.CreateUserHasRoleDto;
import org.example.apirest.dto.userHasRole.UserHasRoleDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.userHasRole.UserHasRoleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user-has-role")
@CrossOrigin(origins = "*")
public class UserHasRoleController extends GeneralizedController<UserHasRoleDto, CreateUserHasRoleDto> {
    public UserHasRoleController(UserHasRoleServiceImpl service) {
        super(service);
    }
}
