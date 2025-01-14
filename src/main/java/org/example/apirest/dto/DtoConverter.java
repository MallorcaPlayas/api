package org.example.apirest.dto;

import java.util.List;

// E => Entity
// D => Dto
// DC => DtoCreate

public interface DtoConverter<E, D, DC> {

    D convertDto(E entity);

    List<D> convertDtoList(List<E> entities);

    E convertToEntityFromDto(D dto);

    E convertToEntityFromCreateDto(DC createDto);

    List<E> convertToEntityListFromCreateDto(List<DC> createDtos);
}
