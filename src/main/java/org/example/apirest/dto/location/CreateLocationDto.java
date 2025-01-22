package org.example.apirest.dto.location;

import lombok.Data;

@Data
public class CreateLocationDto{
    private Double coordinateX;
    private Double coordinateY;
    private Long routeId;
}
