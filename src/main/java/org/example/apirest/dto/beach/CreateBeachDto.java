package org.example.apirest.dto.beach;

import lombok.Data;
import org.example.apirest.dto.service.CreateServiceDto;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;

import java.util.List;

@Data
public class CreateBeachDto {
    private String name;
    private String information;
    private List<CreateServiceDto> services;
    private List<CreateTypeBeachDto> types;
}
