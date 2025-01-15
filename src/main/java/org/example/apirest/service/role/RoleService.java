package org.example.apirest.service.role;

import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.role.CreateRoleDto;
import org.example.apirest.dto.role.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> findAll();
    RoleDto findOne(Long id);
    RoleDto save(CreateRoleDto beach);
    RoleDto update(Long id, CreateRoleDto beach);
    void delete(Long id);
}
