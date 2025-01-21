package org.example.apirest.dto.role;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDto extends BaseDto {
    private String name;
}
