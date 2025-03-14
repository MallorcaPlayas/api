package org.example.apirest.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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


//    private List<UserRequireRole> userRequireRoles;

    @Override
    public String toString() {
        return "UsertDtoUpdate{" +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", privatePrivacy=" + privatePrivacy +
                ", state=" + state +
                '}';
    }
}
