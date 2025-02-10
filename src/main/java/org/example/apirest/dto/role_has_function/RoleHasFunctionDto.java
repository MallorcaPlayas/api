package org.example.apirest.dto.role_has_function;

import lombok.Data;
import org.example.apirest.dto.function.FunctionDto;

@Data
public class RoleHasFunctionDto{
    private Long id;
    private FunctionDto function;
}
