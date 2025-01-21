package org.example.apirest.dto.beachHasService;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.service.ServiceBeachDto;

import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class BeachHasServiceDto extends BaseDto {
    private Long beachId;
    private Long serviceBeachId;
    private LocalTime startTime;
    private LocalTime endTime;
}
