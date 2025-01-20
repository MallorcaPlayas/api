package org.example.apirest.dto.userHasRole;

import lombok.Data;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.model.User;

import java.time.LocalDate;


@Data
public class UserHasRoleDto {
    private Long id;
    private UserDto user;
    private RoleDto role;
    private LocalDate dateBegin;
    private LocalDate dateFinish;
}
