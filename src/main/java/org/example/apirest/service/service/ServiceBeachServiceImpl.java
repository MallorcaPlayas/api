package org.example.apirest.service.service;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.service.CreateServiceDto;
import org.example.apirest.dto.service.ServiceDto;
import org.example.apirest.dto.service.ServiceDtoConvertor;
import org.example.apirest.model.ServiceBeach;
import org.example.apirest.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceBeachServiceImpl implements ServiceBeachService {

    private final ServiceRepository serviceRepository;
    private final ServiceDtoConvertor serviceDtoConverter;

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
        ServiceBeach serviceToInsert = serviceDtoConverter.convertToEntityFromCreateDto(service);
        System.out.println("Beaches en ServiceBeach antes de guardar: " + serviceToInsert.getBeaches());
        return serviceDtoConverter.convertDto(serviceRepository.save(serviceToInsert));
    }

    @Override
    public ServiceDto update(Long id, CreateServiceDto service) {
        ServiceBeach oldService = serviceRepository.findById(id).orElse(null);
        ServiceBeach serviceToInsert = serviceDtoConverter.convertToEntityFromCreateDto(service);

        if (oldService == null) {
            return null;
        }

        oldService.setName(serviceToInsert.getName());
        oldService.setStartTime(serviceToInsert.getStartTime());
        oldService.setEndTime(serviceToInsert.getEndTime());
        oldService.setBeaches(serviceToInsert.getBeaches());


        return serviceDtoConverter.convertDto(serviceRepository.save(oldService));
    }

    @Override
    public void delete(Long id) {
        ServiceBeach serviceBeach = serviceRepository.findById(id).orElse(null);

        serviceRepository.delete(serviceBeach);
    }
}
