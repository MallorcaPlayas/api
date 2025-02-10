package org.example.apirest.dto.location;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LocationDto{
    private Long id;
    private Double longitude;
    private Double latitude;
    private Double elevation;
    private LocalDateTime time;
}
