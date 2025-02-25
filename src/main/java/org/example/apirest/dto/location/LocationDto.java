package org.example.apirest.dto.location;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.photo.PhotoDto;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class LocationDto extends BaseDto {
    private Double longitude;
    private Double latitude;
    private Double elevation;
    private LocalDateTime time;
}
