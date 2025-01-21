package org.example.apirest.dto.userRequireRole;

import lombok.Data;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.dto.user.UserDto;

import java.time.LocalDate;


@Data
public class UserRequireRoleDto extends BaseDto {
    private UserDto user;
    private RoleDto role;
    private String urlPhotoDni;
    private String urlOfficialDoc;
    private boolean isApproved;
}
