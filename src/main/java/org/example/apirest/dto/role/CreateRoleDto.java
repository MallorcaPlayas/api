package org.example.apirest.dto.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;
import org.example.apirest.dto.role_has_function.CreateRoleHasFunctionDto;
import org.example.apirest.model.Function;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateRoleDto extends BaseCreateDto {
    private Long price;
    private String description;
    @JsonProperty("functions")
    private List<CreateRoleHasFunctionDto> roleHasFunctions;
}
