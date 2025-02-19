package org.example.apirest.dto;

import lombok.RequiredArgsConstructor;
import org.example.apirest.model.BaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DtoConverterGeneralizedImpl<Entity extends BaseEntity, Dto, CreateDto> implements DtoConverterGeneralized<Entity, Dto, CreateDto> {

    private final ModelMapper modelMapper;

    @Override
    public Dto convertDto(Entity entity, Class<Dto> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    @Override
    public List<Dto> convertDtoList(List<Entity> entities, Class<Dto> dtoClass) {
        return entities.stream().map(entity -> convertDto(entity, dtoClass)).toList();
    }

    @Override
    public Entity convertToEntityFromDto(Dto dto, Class<Entity> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    @Override
    public Entity convertToEntityFromCreateDto(CreateDto createDto, Class<Entity> entityClass) {
        Entity entity = modelMapper.map(createDto, entityClass);
        entity.setId(null);
        return entity;
    }

    @Override
    public List<Entity> convertToEntityListFromCreateDto(List<CreateDto> createDtos, Class<Entity> entityClass) {
        return createDtos.stream().map(createDto -> convertToEntityFromCreateDto(createDto, entityClass)).toList();
    }

    @Override
    public List<Entity> convertToEntityListFromDto(List<Dto> dtos, Class<Entity> entityClass) {
        return dtos.stream().map(createDto -> convertToEntityFromDto(createDto, entityClass)).toList();
    }
}
