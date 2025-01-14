package org.example.apirest.dto.beach;

import lombok.Data;
import org.example.apirest.dto.service.ServiceDto;
import org.example.apirest.model.TypeBeach;

import java.util.List;

@Data
public class BeachDto {
    private Long id;
    private String name;
    private String information;
    private List<ServiceDto> services;
    private List<TypeBeach> typesBeach;
}
