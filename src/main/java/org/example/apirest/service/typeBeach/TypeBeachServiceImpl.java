package org.example.apirest.service.typeBeach;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.model.TypeBeach;
import org.example.apirest.repository.TypeBeachRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TypeBeachServiceImpl extends GeneralizedServiceImpl<TypeBeach, TypeBeachDto, CreateTypeBeachDto, TypeBeachRepository> {
    public TypeBeachServiceImpl(TypeBeachRepository repository, DtoConverterGeneralizedImpl<TypeBeach,TypeBeachDto,CreateTypeBeachDto> dtoConverter) {
        super(repository, dtoConverter, TypeBeach.class, TypeBeachDto.class);
    }
}
