package org.example.apirest.controller;


import org.example.apirest.dto.role.CreateRoleDto;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.service.role.RoleServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RoleController extends GeneralizedController<RoleDto, CreateRoleDto> {
    public RoleController(RoleServiceImpl service) {
        super(service);
    }
}
