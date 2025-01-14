package org.example.apirest.dto.beach;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.service.ServiceDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BeachDto extends BaseDto {
    private String description;
    private List<ServiceDto> services;
    private List<TypeBeachDto> types;
}
