package org.example.apirest.dto.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.example.apirest.dto.role_has_function.CreateRoleHasFunctionDto;
import java.util.List;

@Data
public class CreateRoleDto{
    private String name;
    private Long price;
    private String description;
    @JsonProperty("functions")
    private List<CreateRoleHasFunctionDto> roleHasFunctions;
}
