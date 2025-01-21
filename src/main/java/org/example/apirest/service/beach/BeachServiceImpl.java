package org.example.apirest.service.beach;

import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.model.Beach;
import org.example.apirest.repository.BeachRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BeachServiceImpl extends GeneralizedServiceImpl<Beach,BeachDto,CreateBeachDto,BeachRepository> {
    public BeachServiceImpl(BeachRepository repository, DtoConverterImpl<Beach,BeachDto,CreateBeachDto> dtoConverter) {
        super(repository, dtoConverter, Beach.class, BeachDto.class);
    }
}
