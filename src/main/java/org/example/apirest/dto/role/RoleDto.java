package org.example.apirest.dto.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.role_has_function.RoleHasFunctionDto;

import java.util.Date;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDto extends BaseDto {
    private String name;
    private Long price;
    private String description;
    @JsonProperty("functions")
    private List<RoleHasFunctionDto> roleHasFunctions;
}
