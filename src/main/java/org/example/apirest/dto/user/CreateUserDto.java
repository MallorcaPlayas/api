package org.example.apirest.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;
import org.example.apirest.dto.organization.OrganizationDto;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.dto.userHasRole.CreateUserHasRoleDto;
import org.example.apirest.dto.userHasRole.UserHasRoleDto;
import org.example.apirest.model.Role;
import org.example.apirest.model.UserHasRole;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateUserDto extends BaseCreateDto {
    private String userName;
    private String firstSurname;
    private String secondSurname;
    private String email;
    private Date birthday;
    private String password;
    private String profilePicture;
    private Boolean privatePrivacy;
    private Boolean state;
    private OrganizationDto organization;
    private List<CreateUserHasRoleDto> roles;

    @Override
    public String toString() {
        return "CreateUserDto{" +
                "userName='" + userName + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", password='" + password + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", privatePrivacy=" + privatePrivacy +
                ", state=" + state +
                ", organization=" + organization +
                ", roles=" + roles +
                '}';
    }
}
