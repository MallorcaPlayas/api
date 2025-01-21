package org.example.apirest.dto.organization;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.user.UserDto;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrganizationDto extends BaseDto {
    private String documentationUrl;
    private String contactNumber;
    private List<UserDto> users;
}
