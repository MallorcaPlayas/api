package org.example.apirest.service;

import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.model.Beach;

import java.util.List;

public interface DtoConverter<Entity,EntityDto,CreateEntityDto> {
    EntityDto toDto(Entity entity);
    List<EntityDto> toDtoList(List<Entity> entities);
    Entity fromDto(CreateEntityDto createEntityDto);
    List<Entity> fromDtoList(List<CreateEntityDto> createEntityDtos);

}
