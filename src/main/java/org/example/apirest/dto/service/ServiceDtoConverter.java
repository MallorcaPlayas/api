package org.example.apirest.dto.service;

import lombok.RequiredArgsConstructor;
import org.example.apirest.model.ServiceBeach;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ServiceDtoConverter {
    private final ModelMapper modelMapper;

    public ServiceDto convertDto(ServiceBeach serviceBeach) {
        return modelMapper.map(serviceBeach, ServiceDto.class);
    }

    public List<ServiceDto> convertDtoList(List<ServiceBeach> serviceBeachList) {
        return serviceBeachList.stream().map(this::convertDto).toList();
    }

    public ServiceBeach convertToCreateServiceEntity(ServiceDto serviceDto) {
        return modelMapper.map(serviceDto, ServiceBeach.class);
    }

    public ServiceBeach convertToCreateServiceEntity(CreateServiceDto createServiceDto) {
        ServiceBeach serviceBeach = modelMapper.map(createServiceDto, ServiceBeach.class);
        serviceBeach.setId(null);
        return serviceBeach;
    }

    public List<ServiceBeach> convertToEntityList(List<CreateServiceDto> serviceDtos) {
        return serviceDtos.stream().map(this::convertToCreateServiceEntity).toList();
    }
}
