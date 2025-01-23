package org.example.apirest.dto.route;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.model.User;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateRouteDto extends BaseCreateDto {
    private double distance;
    private double duration;
    private double elevation;
    private boolean isPrivate;
    private Long userId;
}
