package org.example.apirest.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends BaseDto {
    private String first_name;
    private String last_name;
    private String second_last_name;
    private Date birthday;
    private String urlPhoto;
    private boolean privatePrivacy;
}
