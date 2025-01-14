package org.example.apirest.factory;

import org.example.apirest.dto.DtoConverter;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.model.BaseEntity;
import org.modelmapper.ModelMapper;

public class DtoConverterFactory {

    public <Entity extends BaseEntity, Dto, CreateDto> DtoConverter<Entity, Dto, CreateDto> getConverter(Class<Entity> entityClass, Class<Dto> dtoClass, Class<CreateDto> createDtoClass) {
        return new DtoConverterImpl<>(new ModelMapper());
    }
}
