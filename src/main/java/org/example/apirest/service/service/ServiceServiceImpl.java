package org.example.apirest.service.service;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.service.CreateServiceDto;
import org.example.apirest.dto.service.ServiceDto;
import org.example.apirest.dto.service.ServiceDtoConverter;
import org.example.apirest.model.Beach;
import org.example.apirest.model.ServiceBeach;
import org.example.apirest.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceDtoConverter serviceDtoConverter;

    @Override
    public List<ServiceDto> findAll() {
        return serviceDtoConverter.convertDtoList(serviceRepository.findAll());
    }

    @Override
    public ServiceDto findOne(Long id) {
        ServiceBeach beach = serviceRepository.findById(id).orElse(null);
        return serviceDtoConverter.convertDto(beach);
    }

    @Override
    public ServiceDto save(CreateServiceDto service) {
        ServiceBeach serviceToInsert = serviceDtoConverter.convertToCreateServiceEntity(service);
        return serviceDtoConverter.convertDto(serviceRepository.save(serviceToInsert));
    }

    @Override
    public ServiceDto update(Long id, CreateServiceDto service) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
