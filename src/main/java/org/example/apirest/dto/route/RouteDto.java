package org.example.apirest.dto.route;

import lombok.Data;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.user.UserDto;
import java.util.List;

@Data
public class RouteDto{
    private Long id;
    private String name;
    private double distance;
    private double duration;
    private double elevationAsc;
    private double elevationDesc;
    private boolean isPrivate;
    private List<LocationDto> locations;
    private UserDto user;
}
