package org.example.apirest.dto.location;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class LocationDto extends BaseDto {
    private Double longitude;
    private Double latitude;
    private Double elevation;
    private LocalDateTime time;
    private Long routeId;
}
