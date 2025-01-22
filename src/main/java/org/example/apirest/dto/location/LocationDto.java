package org.example.apirest.dto.location;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class LocationDto extends BaseDto {
    private Double coordinateX;
    private Double coordinateY;
    private Long routeId;
}
