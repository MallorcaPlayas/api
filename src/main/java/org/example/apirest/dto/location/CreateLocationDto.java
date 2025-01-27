package org.example.apirest.dto.location;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateLocationDto{
    private Double latitude;
    private Double longitude;
    private LocalDateTime time;
    private Long routeId;
}
