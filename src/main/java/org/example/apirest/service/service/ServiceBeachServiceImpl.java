package org.example.apirest.service.service;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.service.CreateServiceBeachDto;
import org.example.apirest.dto.service.ServiceBeachDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.ServiceBeach;
import org.example.apirest.repository.ServiceRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceBeachServiceImpl implements ServiceBeachService {

    private final ServiceRepository repository;
    private final DtoConverterImpl<ServiceBeach, ServiceBeachDto, CreateServiceBeachDto> dtoConverter;

    @Override
    public List<ServiceBeachDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), ServiceBeachDto.class);
    }

    @Override
    public ServiceBeachDto findOne(Long id) {
        ServiceBeach beach = repository.findById(id).orElseThrow(()-> new NotFoundException(ServiceBeach.class,id));
        return dtoConverter.convertDto(beach, ServiceBeachDto.class);
    }

    @Override
    public ServiceBeachDto save(CreateServiceBeachDto service) {
        ServiceBeach serviceToInsert = dtoConverter.convertToEntityFromCreateDto(service, ServiceBeach.class);
        return dtoConverter.convertDto(repository.save(serviceToInsert), ServiceBeachDto.class);
    }

    @Override
    public ServiceBeachDto update(Long id, CreateServiceBeachDto service) {
        ServiceBeach oldService = repository.findById(id).orElseThrow(()-> new NotFoundException(ServiceBeach.class,id));
        ServiceBeach serviceToInsert = dtoConverter.convertToEntityFromCreateDto(service, ServiceBeach.class);

        if (oldService == null) {
            return null;
        }

        UtilsClass.updateFields(oldService, serviceToInsert);

        return dtoConverter.convertDto(repository.save(oldService), ServiceBeachDto.class);
    }

    @Override
    public void delete(Long id) {
        ServiceBeach serviceBeach = repository.findById(id).orElseThrow(()-> new NotFoundException(ServiceBeach.class,id));

        repository.delete(serviceBeach);
    }
}
