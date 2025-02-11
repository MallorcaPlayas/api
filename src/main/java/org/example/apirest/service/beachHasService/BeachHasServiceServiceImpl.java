package org.example.apirest.service.beachHasService;

import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.beachHasService.BeachHasServiceDto;
import org.example.apirest.dto.beachHasService.CreateBeachHasServiceDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.repository.*;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeachHasServiceServiceImpl{

    private final BeachRepository beachRepository;
    private final ServiceRepository serviceRepository;
    private BeachHasServiceRepository repository;

    public List<BeachHasServiceDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), dtoClass);
    }

    public Dto findOne(Long id) {
        Entity entity = repository.findById(id).orElseThrow(()-> new NotFoundException(entityClass,id));
        return dtoConverter.convertDto(entity, dtoClass);
    }

    public void delete(Long id) {
        Entity entity = repository.findById(id).orElseThrow(()-> new NotFoundException(entityClass,id));
        repository.delete(entity);
    }

    public BeachHasServiceServiceImpl(BeachHasServiceRepository repository, DtoConverterImpl<BeachHasService,BeachHasServiceDto,CreateBeachHasServiceDto> dtoConverter, BeachRepository beachRepository, ServiceRepository serviceRepository) {
        super(repository, dtoConverter, BeachHasService.class, BeachHasServiceDto.class);
        this.beachRepository = beachRepository;
        this.serviceRepository = serviceRepository;
    }

    public BeachHasServiceDto save(CreateBeachHasServiceDto entity) {
        BeachHasService entityToInsert = dtoConverter.convertToEntityFromCreateDto(entity, BeachHasService.class);
        Beach beach = beachRepository.findById(entity.getBeach_id()).orElseThrow(()-> new NotFoundException(Beach.class,entity.getBeach_id()));
        ServiceBeach service = serviceRepository.findById(entity.getServiceBeach().getId()).orElseThrow(()-> new NotFoundException(Role.class,entity.getServiceBeach().getId()));
        entityToInsert.setBeach(beach);
        entityToInsert.setServiceBeach(service);
        return dtoConverter.convertDto(repository.save(entityToInsert), BeachHasServiceDto.class);
    }

    public BeachHasServiceDto update(Long id, CreateBeachHasServiceDto entity) {
        BeachHasService old = repository.findById(id).orElseThrow(()-> new NotFoundException(BeachHasService.class,id));
        BeachHasService newEntity = dtoConverter.convertToEntityFromCreateDto(entity, BeachHasService.class);

        if (entity == null) {
            return null;
        }

        UtilsClass.updateFields(old, newEntity);

        Beach beach = beachRepository.findById(entity.getBeach_id()).orElseThrow(()-> new NotFoundException(Beach.class,entity.getBeach_id()));
        ServiceBeach service = serviceRepository.findById(entity.getServiceBeach().getId()).orElseThrow(()-> new NotFoundException(Role.class,entity.getServiceBeach().getId()));
        old.setBeach(beach);
        old.setServiceBeach(service);

        return dtoConverter.convertDto(repository.save(old), BeachHasServiceDto.class);
    }

    public BeachHasServiceDto toDto
}
