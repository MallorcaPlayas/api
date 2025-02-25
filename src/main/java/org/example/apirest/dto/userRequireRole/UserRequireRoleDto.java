package org.example.apirest.dto.userRequireRole;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.document.DocumentDto;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.dto.user.UserDto;

import java.time.LocalDate;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class UserRequireRoleDto extends BaseDto {
    private UserDto user;
    private RoleDto role;
    private List<DocumentDto> documents;
    private boolean isApproved;
}
