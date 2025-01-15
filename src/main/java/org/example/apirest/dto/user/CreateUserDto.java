package org.example.apirest.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateUserDto extends BaseCreateDto {
    private String first_name;
    private String last_name;
    private String second_last_name;
    private String email;
    private Date birthday;
    private String password;
    private String urlPhoto;
    private boolean privatePrivacy;
}
