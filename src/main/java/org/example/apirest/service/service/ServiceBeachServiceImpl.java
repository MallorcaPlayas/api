package org.example.apirest.service.service;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.service.CreateServiceBeachDto;
import org.example.apirest.dto.service.ServiceBeachDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Route;
import org.example.apirest.model.ServiceBeach;
import org.example.apirest.repository.RouteRepository;
import org.example.apirest.repository.ServiceRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceBeachServiceImpl extends GeneralizedServiceImpl<ServiceBeach, ServiceBeachDto, CreateServiceBeachDto, ServiceRepository> {
    public ServiceBeachServiceImpl(ServiceRepository repository, DtoConverterImpl<ServiceBeach,ServiceBeachDto,CreateServiceBeachDto> dtoConverter) {
        super(repository, dtoConverter, ServiceBeach.class, ServiceBeachDto.class);
    }
}
