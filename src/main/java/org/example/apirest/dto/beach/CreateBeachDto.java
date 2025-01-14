package org.example.apirest.dto.beach;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;
import org.example.apirest.dto.service.CreateServiceDto;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateBeachDto extends BaseCreateDto {
    private String information;
    private List<CreateServiceDto> services;
    private List<CreateTypeBeachDto> types;
}
