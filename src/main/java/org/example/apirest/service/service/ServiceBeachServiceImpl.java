package org.example.apirest.service.service;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.service.CreateServiceBeachDto;
import org.example.apirest.dto.service.ServiceBeachDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.ServiceBeach;
import org.example.apirest.repository.ServiceRepository;
import org.example.apirest.utils.UtilsClass;
import org.example.apirest.service.DtoConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceBeachServiceImpl implements DtoConverter<ServiceBeach, ServiceBeachDto, CreateServiceBeachDto> {

    private final ServiceRepository repository;
    private final ModelMapper mapper;

    public List<ServiceBeachDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public ServiceBeachDto findOne(Long id) {
        ServiceBeach entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ServiceBeach.class, id));
        return this.toDto(entity);
    }

    public ServiceBeachDto save(CreateServiceBeachDto createDto) {
        ServiceBeach entity = fromDto(createDto);
        ServiceBeach savedEntity = repository.save(entity);
        return toDto(savedEntity);
    }

    public ServiceBeachDto update(Long id, CreateServiceBeachDto createDto) {
        ServiceBeach oldEntity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ServiceBeach.class, id));
        ServiceBeach entityToUpdate = fromDto(createDto);

        UtilsClass.updateFields(oldEntity, entityToUpdate);

        ServiceBeach savedEntity = repository.save(oldEntity);
        return toDto(savedEntity);
    }

    public void delete(Long id) {
        ServiceBeach entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ServiceBeach.class, id));
        repository.delete(entity);
    }

    @Override
    public ServiceBeachDto toDto(ServiceBeach entity) {
        return mapper.map(entity, ServiceBeachDto.class);
    }

    @Override
    public List<ServiceBeachDto> toDtoList(List<ServiceBeach> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public ServiceBeach fromDto(CreateServiceBeachDto createDto) {
        return mapper.map(createDto, ServiceBeach.class);
    }

    @Override
    public List<ServiceBeach> fromDtoList(List<CreateServiceBeachDto> createDtos) {
        return createDtos.stream()
                .map(this::fromDto)
                .toList();
    }
}
