package org.example.apirest.dto.service;

import lombok.RequiredArgsConstructor;
import org.example.apirest.model.Service;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ServiceDtoConverter {
    private final ModelMapper modelMapper;

    public ServiceDto convertDto(Service service) {
        return modelMapper.map(service, ServiceDto.class);
    }

    public List<ServiceDto> convertDtoList(List<Service> serviceList) {
        return serviceList.stream().map(this::convertDto).toList();
    }

    public Service convertToCreateServiceEntity(ServiceDto serviceDto) {
        return modelMapper.map(serviceDto, Service.class);
    }

    public Service convertToCreateServiceEntity(CreateServiceDto createServiceDto) {
        Service service = modelMapper.map(createServiceDto, Service.class);
        service.setId(null);
        return service;
    }

    public List<Service> convertToEntityList(List<CreateServiceDto> serviceDtos) {
        return serviceDtos.stream().map(this::convertToCreateServiceEntity).toList();
    }
}
