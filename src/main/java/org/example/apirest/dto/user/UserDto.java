package org.example.apirest.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.organization.OrganizationDto;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.dto.userHasRole.UserHasRoleDto;
import org.example.apirest.model.Organization;
import org.example.apirest.model.Photo;
import org.example.apirest.model.Role;
import org.example.apirest.model.UserHasRole;

import java.util.Date;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends BaseDto {
    private String name;
    private String userName;
    private String firstSurname;
    private String secondSurname;
    private String email;
    private String password;
    private Date birthday;
    private String profilePicture;
    private Boolean privatePrivacy;
    private Boolean state;
    private OrganizationDto organization;
    private List<UserHasRoleDto> roles;
    private List<PhotoDto> photo;

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", profilePicture='" + profilePicture + '\'' +
                ", privatePrivacy=" + privatePrivacy +
                ", state=" + state +
                ", organization=" + organization +
                ", roles=" + roles +
                ", photo=" + photo +
                '}';
    }
}