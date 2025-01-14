package org.example.apirest.dto.service;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.model.ServiceBeach;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ServiceDtoConvertor implements DtoConverter<ServiceBeach, ServiceDto, CreateServiceDto> {
    private final ModelMapper modelMapper;

    public ServiceDto convertDto(ServiceBeach serviceBeach) {
        return modelMapper.map(serviceBeach, ServiceDto.class);
    }

    public List<ServiceDto> convertDtoList(List<ServiceBeach> serviceBeachList) {
        return serviceBeachList.stream().map(this::convertDto).toList();
    }

    public ServiceBeach convertToEntityFromDto(ServiceDto serviceDto) {
        return modelMapper.map(serviceDto, ServiceBeach.class);
    }

    public ServiceBeach convertToEntityFromCreateDto(CreateServiceDto createServiceDto) {
        ServiceBeach serviceBeach = modelMapper.map(createServiceDto, ServiceBeach.class);
        serviceBeach.setId(null);
        return serviceBeach;
    }

    public List<ServiceBeach> convertToEntityListFromCreateDto(List<CreateServiceDto> serviceDtos) {
        return serviceDtos.stream().map(this::convertToEntityFromCreateDto).toList();
    }
}