package org.example.apirest.dto.role_has_function;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.function.FunctionDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleHasFunctionDto extends BaseDto {
    private FunctionDto function;
}
