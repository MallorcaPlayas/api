package org.example.apirest.dto.route;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.user.UserDto;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RouteDto extends BaseDto {
    private String name;
    private double distance;
    private double duration;
    private double elevationAsc;
    private double elevationDesc;
    private boolean isPrivate;
    private List<PhotoDto> photos;
    private List<LocationDto> locations;
    private UserDto user;
}
