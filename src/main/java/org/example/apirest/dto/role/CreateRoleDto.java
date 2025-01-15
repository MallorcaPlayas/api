package org.example.apirest.dto.role;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateRoleDto extends BaseCreateDto {
    private Long price;
    private String description;
}
