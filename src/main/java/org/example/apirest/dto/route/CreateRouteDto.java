package org.example.apirest.dto.route;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.model.Location;
import org.example.apirest.model.User;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateRouteDto extends BaseCreateDto {
    private String name;
    private double distance;
    private double duration;
    private double elevation;
    private boolean isPrivate;
    private List<CreateLocationDto> locations;
    private Long userId;
}
