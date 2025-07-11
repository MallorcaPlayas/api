package org.example.apirest.dto;

import java.util.List;

@Deprecated
public interface DtoConverterGeneralized<Entity, Dto, CreateDto> {

    Dto convertDto(Entity entity, Class<Dto> dtoClass);

    List<Dto> convertDtoList(List<Entity> entities, Class<Dto> dtoClass);

    Entity convertToEntityFromDto(Dto dto, Class<Entity> entityClass);

    Entity convertToEntityFromCreateDto(CreateDto createDto, Class<Entity> entityClass);

    List<Entity> convertToEntityListFromCreateDto(List<CreateDto> createDtos, Class<Entity> entityClass);

    List<Entity> convertToEntityListFromDto(List<Dto> dtos, Class<Entity> entityClass);
}
