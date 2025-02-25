package org.example.apirest.service.service;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.service.CreateServiceBeachDto;
import org.example.apirest.dto.service.ServiceBeachDto;
import org.example.apirest.model.ServiceBeach;
import org.example.apirest.repository.ServiceRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ServiceBeachServiceImpl extends GeneralizedServiceImpl<ServiceBeach, ServiceBeachDto, CreateServiceBeachDto, ServiceRepository> {
    public ServiceBeachServiceImpl(ServiceRepository repository, DtoConverterGeneralizedImpl<ServiceBeach,ServiceBeachDto,CreateServiceBeachDto> dtoConverter) {
        super(repository, dtoConverter, ServiceBeach.class, ServiceBeachDto.class);
    }
}
