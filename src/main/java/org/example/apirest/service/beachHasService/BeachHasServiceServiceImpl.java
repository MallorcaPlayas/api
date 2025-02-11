package org.example.apirest.service.beachHasService;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beachHasService.BeachHasServiceDto;
import org.example.apirest.dto.beachHasService.CreateBeachHasServiceDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Beach;
import org.example.apirest.model.BeachHasService;
import org.example.apirest.model.ServiceBeach;
import org.example.apirest.repository.BeachHasServiceRepository;
import org.example.apirest.repository.BeachRepository;
import org.example.apirest.repository.ServiceRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeachHasServiceServiceImpl implements DtoConverter<BeachHasService, BeachHasServiceDto, CreateBeachHasServiceDto> {

    private final BeachHasServiceRepository repository;
    private final BeachRepository beachRepository;
    private final ServiceRepository serviceRepository;
    private final ModelMapper mapper;

    public List<BeachHasServiceDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public BeachHasServiceDto findOne(Long id) {
        BeachHasService beachHasService = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(BeachHasService.class, id));
        return this.toDto(beachHasService);
    }

    public BeachHasServiceDto save(CreateBeachHasServiceDto entity) {
        BeachHasService entityToInsert = fromDto(entity);
        Beach beach = beachRepository.findById(entity.getBeach_id())
                .orElseThrow(() -> new NotFoundException(Beach.class, entity.getBeach_id()));
        ServiceBeach service = serviceRepository.findById(entity.getServiceBeach().getId())
                .orElseThrow(() -> new NotFoundException(ServiceBeach.class, entity.getServiceBeach().getId()));

        entityToInsert.setBeach(beach);
        entityToInsert.setServiceBeach(service);

        return toDto(repository.save(entityToInsert));
    }

    public BeachHasServiceDto update(Long id, CreateBeachHasServiceDto entity) {
        BeachHasService oldEntity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(BeachHasService.class, id));
        BeachHasService newEntity = fromDto(entity);

        UtilsClass.updateFields(oldEntity, newEntity);

        Beach beach = beachRepository.findById(entity.getBeach_id())
                .orElseThrow(() -> new NotFoundException(Beach.class, entity.getBeach_id()));
        ServiceBeach service = serviceRepository.findById(entity.getServiceBeach().getId())
                .orElseThrow(() -> new NotFoundException(ServiceBeach.class, entity.getServiceBeach().getId()));

        oldEntity.setBeach(beach);
        oldEntity.setServiceBeach(service);

        return toDto(repository.save(oldEntity));
    }

    public void delete(Long id) {
        BeachHasService entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(BeachHasService.class, id));
        repository.delete(entity);
    }

    @Override
    public BeachHasServiceDto toDto(BeachHasService entity) {
        return mapper.map(entity, BeachHasServiceDto.class);
    }

    @Override
    public List<BeachHasServiceDto> toDtoList(List<BeachHasService> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public BeachHasService fromDto(CreateBeachHasServiceDto dto) {
        return mapper.map(dto, BeachHasService.class);
    }

    @Override
    public List<BeachHasService> fromDtoList(List<CreateBeachHasServiceDto> dtos) {
        return dtos.stream()
                .map(this::fromDto)
                .toList();
    }
}
