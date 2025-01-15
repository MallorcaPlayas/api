package org.example.apirest.service.service;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.service.CreateServiceBeachDto;
import org.example.apirest.dto.service.ServiceBeachDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Beach;
import org.example.apirest.model.ServiceBeach;
import org.example.apirest.model.TypeBeach;
import org.example.apirest.repository.ServiceRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceBeachServiceImpl implements ServiceBeachService {

    private final ServiceRepository serviceRepository;
    private final DtoConverterImpl<ServiceBeach, ServiceBeachDto, CreateServiceBeachDto> serviceDtoConverter;

    @Override
    public List<ServiceBeachDto> findAll() {
        return serviceDtoConverter.convertDtoList(serviceRepository.findAll(), ServiceBeachDto.class);
    }

    @Override
    public ServiceBeachDto findOne(Long id) {
        ServiceBeach beach = serviceRepository.findById(id).orElseThrow(()-> new NotFoundException(ServiceBeach.class,id));
        return serviceDtoConverter.convertDto(beach, ServiceBeachDto.class);
    }

    @Override
    public ServiceBeachDto save(CreateServiceBeachDto service) {
        ServiceBeach serviceToInsert = serviceDtoConverter.convertToEntityFromCreateDto(service, ServiceBeach.class);
        System.out.println("Beaches en ServiceBeach antes de guardar: " + serviceToInsert.getBeaches());
        return serviceDtoConverter.convertDto(serviceRepository.save(serviceToInsert), ServiceBeachDto.class);
    }

    @Override
    public ServiceBeachDto update(Long id, CreateServiceBeachDto service) {
        ServiceBeach oldService = serviceRepository.findById(id).orElseThrow(()-> new NotFoundException(ServiceBeach.class,id));
        ServiceBeach serviceToInsert = serviceDtoConverter.convertToEntityFromCreateDto(service, ServiceBeach.class);

        if (oldService == null) {
            return null;
        }

        UtilsClass.updateFields(oldService, serviceToInsert);

        return serviceDtoConverter.convertDto(serviceRepository.save(oldService), ServiceBeachDto.class);
    }

    @Override
    public void delete(Long id) {
        ServiceBeach serviceBeach = serviceRepository.findById(id).orElseThrow(()-> new NotFoundException(ServiceBeach.class,id));

        serviceRepository.delete(serviceBeach);
    }
}
