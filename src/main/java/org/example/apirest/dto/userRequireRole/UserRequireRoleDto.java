package org.example.apirest.dto.userRequireRole;

import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.dto.user.UserDto;

import java.time.LocalDate;
import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Data
public class UserRequireRoleDto extends BaseDto {
    private UserDto user;
    private RoleDto role;
    private LocalDateTime created;
    private LocalDateTime updated;
    private boolean isApproved;
}
