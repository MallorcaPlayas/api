package org.example.apirest.dto.beach;

import lombok.Data;
import org.example.apirest.dto.service.ServiceDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;

import java.util.List;

@Data
public class BeachDto {
    private String name;
    private String description;
    private List<ServiceDto> services;
    private List<TypeBeachDto> types;
}
