package org.example.apirest.dto.service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceBeachDto extends BaseDto {
    private LocalTime startTime;
    private LocalTime endTime;
}
