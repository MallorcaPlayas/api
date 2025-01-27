package org.example.apirest.controller;

import org.example.apirest.dto.userRequireRole.CreateUserRequireRoleDto;
import org.example.apirest.dto.userRequireRole.UserRequireRoleDto;
import org.example.apirest.service.userRequireRole.UserRequireRoleServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-require-role")
@CrossOrigin(origins = "*")
public class UserRequireRoleController extends GeneralizedController<UserRequireRoleDto, CreateUserRequireRoleDto> {
    public UserRequireRoleController(UserRequireRoleServiceImpl  service) {
        super(service);
    }
}
