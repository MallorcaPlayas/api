package org.example.apirest.dto;


import java.util.List;

public interface DtoConverter<Entity,Dto,CreateDto> {
    Dto entityToDto(Entity entity);
    List<Dto> entityListToDtoList(List<Entity> entities);
    Entity createDtoToEntity(CreateDto createDto);
    List<Entity> createDtoListToEntityList(List<CreateDto> createDtos);
}
