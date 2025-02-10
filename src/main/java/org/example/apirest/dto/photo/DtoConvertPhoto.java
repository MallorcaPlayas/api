package org.example.apirest.dto.photo;

import org.example.apirest.dto.DtoConverter;

import java.util.List;

public class DtoConvertPhoto implements DtoConverter<Entity, Dto, CreateDto> {
    @Override
    public Object convertDto(Object object, Class aClass) {
        return null;
    }

    @Override
    public List convertDtoList(List list, Class aClass) {
        return List.of();
    }

    @Override
    public Object convertToEntityFromDto(Object object, Class aClass) {
        return null;
    }

    @Override
    public Object convertToEntityFromCreateDto(Object object, Class aClass) {
        return null;
    }

    @Override
    public List convertToEntityListFromCreateDto(List list, Class aClass) {
        return List.of();
    }

    @Override
    public List convertToEntityListFromDto(List list, Class aClass) {
        return List.of();
    }
}
