package org.example.apirest.dto.location;

import lombok.Data;
import org.example.apirest.dto.BaseDto;

@Data
public class LocationDto {
    private Long id;
    private Double coordinateX;
    private Double coordinateY;
}
