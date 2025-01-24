package org.example.apirest.dto.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.beachHasService.BeachHasServiceDto;

import java.time.LocalTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceBeachDto extends BaseDto {
    private String name;
}
