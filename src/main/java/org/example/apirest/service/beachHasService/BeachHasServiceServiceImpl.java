package org.example.apirest.service.beachHasService;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class BeachHasServiceServiceImpl implements BeachHasServiceService {

    private final BeachHasServiceRepository repository;
    private final BeachRepository beachRepository;
    private final ServiceRepository serviceRepository;
    private final DtoConverterImpl<BeachHasService, BeachHasServiceDto, CreateBeachHasServiceDto> serviceDtoConverter;

    @Override
    public List<BeachHasServiceDto> findAll() {
        return serviceDtoConverter.convertDtoList(repository.findAll(), BeachHasServiceDto.class);
    }

    @Override
    public BeachHasServiceDto findOne(Long id) {
        BeachHasService entity = repository.findById(id).orElseThrow(()-> new NotFoundException(BeachHasService.class,id));
        return serviceDtoConverter.convertDto(entity, BeachHasServiceDto.class);
    }

    @Override
    public BeachHasServiceDto save(CreateBeachHasServiceDto entity) {
        BeachHasService entityToInsert = serviceDtoConverter.convertToEntityFromCreateDto(entity, BeachHasService.class);
        Beach beach = beachRepository.findById(entity.getBeach_id()).orElseThrow(()-> new NotFoundException(Beach.class,entity.getBeach_id()));
        ServiceBeach service = serviceRepository.findById(entity.getServiceBeach_id()).orElseThrow(()-> new NotFoundException(Role.class,entity.getServiceBeach_id()));
        entityToInsert.setBeach(beach);
        entityToInsert.setServiceBeach(service);
        return serviceDtoConverter.convertDto(repository.save(entityToInsert), BeachHasServiceDto.class);
    }

    @Override
    public BeachHasServiceDto update(Long id, CreateBeachHasServiceDto entity) {
        BeachHasService old = repository.findById(id).orElseThrow(()-> new NotFoundException(BeachHasService.class,id));
        BeachHasService newEntity = serviceDtoConverter.convertToEntityFromCreateDto(entity, BeachHasService.class);

        if (entity == null) {
            return null;
        }

        UtilsClass.updateFields(old, newEntity);

        Beach beach = beachRepository.findById(entity.getBeach_id()).orElseThrow(()-> new NotFoundException(Beach.class,entity.getBeach_id()));
        ServiceBeach service = serviceRepository.findById(entity.getServiceBeach_id()).orElseThrow(()-> new NotFoundException(Role.class,entity.getServiceBeach_id()));
        old.setBeach(beach);
        old.setServiceBeach(service);

        return serviceDtoConverter.convertDto(repository.save(old), BeachHasServiceDto.class);
    }

    @Override
    public void delete(Long id) {
        BeachHasService entity = repository.findById(id).orElseThrow(()-> new NotFoundException(BeachHasService.class,id));
        repository.delete(entity);
    }
}
