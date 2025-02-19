package org.example.apirest.dto;


import java.util.List;

public interface DtoConverter<Entity,Dto> {
    Dto entityToDto(Entity entity);
    List<Dto> entityListToDtoList(List<Entity> entities);
    Entity dtoToEntity(Dto dto);
    List<Entity> dtoListToEntityList(List<Dto> dtos);
}
