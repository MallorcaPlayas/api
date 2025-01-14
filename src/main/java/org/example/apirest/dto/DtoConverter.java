package org.example.apirest.dto;

import java.util.List;

public interface DtoConverter<Entity, Dto, CreateDto> {

    Dto convertDto(Entity entity);

    List<Dto> convertDtoList(List<Entity> entities);

    Entity convertToEntityFromDto(Dto dto);

    Entity convertToEntityFromCreateDto(CreateDto createDto);

    List<Entity> convertToEntityListFromCreateDto(List<CreateDto> createDtos);
}
