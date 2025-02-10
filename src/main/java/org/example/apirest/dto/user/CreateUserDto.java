package org.example.apirest.dto.user;

import lombok.Data;
import org.example.apirest.dto.organization.OrganizationDto;
import org.example.apirest.dto.userHasRole.CreateUserHasRoleDto;

import java.util.Date;
import java.util.List;

@Data
public class CreateUserDto{
    private String name;
    private String userName;
    private String firstSurname;
    private String secondSurname;
    private String email;
    private Date birthday;
    private String password;
    private String profilePicture;
    private boolean privatePrivacy;
    private boolean state;
    private OrganizationDto organization;
    private List<CreateUserHasRoleDto> roles;
}
