package org.example.apirest.service.typeBeach;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.service.CreateServiceBeachDto;
import org.example.apirest.dto.service.ServiceBeachDto;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.ServiceBeach;
import org.example.apirest.model.TypeBeach;
import org.example.apirest.repository.ServiceRepository;
import org.example.apirest.repository.TypeBeachRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeBeachServiceImpl extends GeneralizedServiceImpl<TypeBeach, TypeBeachDto, CreateTypeBeachDto, TypeBeachRepository> {
    public TypeBeachServiceImpl(TypeBeachRepository repository, DtoConverterImpl<TypeBeach,TypeBeachDto,CreateTypeBeachDto> dtoConverter) {
        super(repository, dtoConverter, TypeBeach.class, TypeBeachDto.class);
    }
}
