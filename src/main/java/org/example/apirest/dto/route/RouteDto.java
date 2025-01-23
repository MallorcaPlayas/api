package org.example.apirest.dto.route;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.model.User;

@EqualsAndHashCode(callSuper = true)
@Data
public class RouteDto extends BaseDto {
    private String name;
    private double distance;
    private double duration;
    private double elevation;
    private boolean isPrivate;
    private UserDto user;
}
