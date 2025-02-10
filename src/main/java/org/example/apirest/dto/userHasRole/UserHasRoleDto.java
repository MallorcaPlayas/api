package org.example.apirest.dto.userHasRole;

import lombok.Data;
import org.example.apirest.dto.role.RoleDto;
import java.time.LocalDate;

@Data
public class UserHasRoleDto{
    private Long id;
    private RoleDto role;
    private LocalDate dateBegin;
    private LocalDate dateFinish;
}
