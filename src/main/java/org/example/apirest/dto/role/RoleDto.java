package org.example.apirest.dto.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.example.apirest.dto.role_has_function.RoleHasFunctionDto;
import java.util.List;

@Data
public class RoleDto{
    private Long id;
    private String name;
    private Long price;
    private String description;
    @JsonProperty("functions")
    private List<RoleHasFunctionDto> roleHasFunctions;
}
