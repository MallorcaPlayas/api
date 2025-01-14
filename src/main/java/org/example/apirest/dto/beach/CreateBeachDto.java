package org.example.apirest.dto.beach;

import lombok.Data;
import org.example.apirest.dto.service.CreateServiceDto;
import org.example.apirest.dto.service.ServiceDto;

import java.util.List;

@Data
public class CreateBeachDto {
    private String name;
    private String information;
    private String typeBeach;
    private List<CreateServiceDto> services;
}
