package org.example.apirest.dto;


import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public interface DtoConverter<Entity,Dto> {

    Dto entityToDto(Entity entity);

    default List<Dto> entityListToDtoList(List<Entity> entities){
        return entities.stream().map(this::entityToDto).toList();
    }

    Entity dtoToEntity(Dto dto);

    default List<Entity> dtoListToEntityList(List<Dto> dtos){
        return dtos.stream().map(this::dtoToEntity).toList();
    }
}
