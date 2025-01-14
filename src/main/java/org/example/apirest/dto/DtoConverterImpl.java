package org.example.apirest.dto;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.model.BaseEntity;
import org.example.apirest.model.Beach;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DtoConverterImpl<Entity extends BaseEntity, Dto, CreateDto> implements DtoConverter<Entity, Dto, CreateDto>{

    private final ModelMapper modelMapper;

    @Override
    public Dto convertDto(Entity entity) {
        return modelMapper.map(entity, (Class<Dto>) entity.getClass());
    }

    @Override
    public List<Dto> convertDtoList(List<Entity> entities) {
        return entities.stream().map(this::convertDto).toList();
    }

    @Override
    public Entity convertToEntityFromDto(Dto dto) {
        return modelMapper.map(dto, (Class<Entity>) dto.getClass());
    }

    @Override
    public Entity convertToEntityFromCreateDto(CreateDto createDto) {
        Entity entity = modelMapper.map(createDto, (Class<Entity>) createDto.getClass());
        entity.setId(null);
        return entity;
    }

    @Override
    public List<Entity> convertToEntityListFromCreateDto(List<CreateDto> createDtos) {
        return createDtos.stream().map(this::convertToEntityFromCreateDto).toList();
    }
}
