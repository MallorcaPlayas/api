package org.example.apirest.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.organization.OrganizationDto;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.dto.userHasRole.UserHasRoleDto;
import org.example.apirest.model.Organization;
import org.example.apirest.model.Role;
import org.example.apirest.model.UserHasRole;

import java.util.Date;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends BaseDto {
    private String userName;
    private String firstSurname;
    private String secondSurname;
    private String email;
    private Date birthday;
    private String urlPhoto;
    private boolean privatePrivacy;
    private boolean state;
    private OrganizationDto organization;
}
