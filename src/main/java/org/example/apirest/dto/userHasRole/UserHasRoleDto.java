package org.example.apirest.dto.userHasRole;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.model.User;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Data
public class UserHasRoleDto extends BaseDto {
    private Long userId;
    private Long roleId;
    private LocalDate dateBegin;
    private LocalDate dateFinish;
}
