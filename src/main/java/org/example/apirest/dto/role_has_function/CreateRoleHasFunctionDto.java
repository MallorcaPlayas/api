package org.example.apirest.dto.role_has_function;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;
import org.example.apirest.dto.function.FunctionDto;
import org.example.apirest.dto.role.RoleDto;

@Data
public class CreateRoleHasFunctionDto{
    private RoleDto role;
    private FunctionDto function;
}
