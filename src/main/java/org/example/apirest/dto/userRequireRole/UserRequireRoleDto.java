package org.example.apirest.dto.userRequireRole;

import lombok.Data;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.dto.user.UserDto;

@Data
public class UserRequireRoleDto{
    private Long id;
    private UserDto user;
    private RoleDto role;
    private String urlPhotoDni;
    private String urlOfficialDoc;
    private boolean isApproved;
}
