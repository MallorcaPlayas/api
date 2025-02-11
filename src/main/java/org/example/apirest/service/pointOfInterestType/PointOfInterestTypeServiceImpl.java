package org.example.apirest.service.pointOfInterestType;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.pointOfInterestType.CreatePointOfInterestTypeDto;
import org.example.apirest.dto.pointOfInterestType.PointOfInterestTypeDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.PointOfInterestType;
import org.example.apirest.repository.PointOfInterestTypeRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointOfInterestTypeServiceImpl implements DtoConverter<PointOfInterestType, PointOfInterestTypeDto, CreatePointOfInterestTypeDto> {

    private final PointOfInterestTypeRepository repository;
    private final ModelMapper mapper;

    public List<PointOfInterestTypeDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public PointOfInterestTypeDto findOne(Long id) {
        PointOfInterestType entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PointOfInterestType.class, id));
        return this.toDto(entity);
    }

    public PointOfInterestTypeDto save(CreatePointOfInterestTypeDto dto) {
        PointOfInterestType entityToInsert = fromDto(dto);
        return toDto(repository.save(entityToInsert));
    }

    public PointOfInterestTypeDto update(Long id, CreatePointOfInterestTypeDto dto) {
        PointOfInterestType oldEntity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PointOfInterestType.class, id));
        PointOfInterestType newEntity = fromDto(dto);

        UtilsClass.updateFields(oldEntity, newEntity);
        return toDto(repository.save(oldEntity));
    }

    public void delete(Long id) {
        PointOfInterestType entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(PointOfInterestType.class, id));
        repository.delete(entity);
    }

    @Override
    public PointOfInterestTypeDto toDto(PointOfInterestType entity) {
        return mapper.map(entity, PointOfInterestTypeDto.class);
    }

    @Override
    public List<PointOfInterestTypeDto> toDtoList(List<PointOfInterestType> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public PointOfInterestType fromDto(CreatePointOfInterestTypeDto dto) {
        return mapper.map(dto, PointOfInterestType.class);
    }

    @Override
    public List<PointOfInterestType> fromDtoList(List<CreatePointOfInterestTypeDto> dtos) {
        return dtos.stream()
                .map(this::fromDto)
                .toList();
    }
}
