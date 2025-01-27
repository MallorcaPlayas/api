package org.example.apirest.controller;

import org.example.apirest.dto.userHasRole.CreateUserHasRoleDto;
import org.example.apirest.dto.userHasRole.UserHasRoleDto;
import org.example.apirest.service.userHasRole.UserHasRoleServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-has-role")
@CrossOrigin(origins = "*")
public class UserHasRoleController extends GeneralizedController<UserHasRoleDto, CreateUserHasRoleDto> {
    public UserHasRoleController(UserHasRoleServiceImpl service) {
        super(service);
    }
}
