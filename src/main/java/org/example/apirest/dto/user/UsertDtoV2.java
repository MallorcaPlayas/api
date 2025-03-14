package org.example.apirest.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.apirest.dto.role.UserRoleDto;
import org.example.apirest.model.UserHasRole;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsertDtoV2 {
    private Long id;
    private String name;

    private String userName;

    private String firstSurname;

    private String secondSurname;


    private String email;

    private Date birthday;

    private Boolean privatePrivacy;

    private Boolean state;

    private List<UserRoleDto> roles;


//    private List<UserRequireRole> userRequireRoles;


    @Override
    public String toString() {
        return "UsertDtoV2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", privatePrivacy=" + privatePrivacy +
                ", state=" + state +
                ", roles=" + roles +
                '}';
    }
}
