package org.example.apirest.dto.route.converters;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.model.location.Location;
import org.example.apirest.model.route.Route;
import org.example.apirest.repository.location.LocationRepositoryFirestore;
import org.example.apirest.service.location.LocationServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateRouteDtoConverter implements DtoConverter<Route, CreateRouteDto> {

    private final ModelMapper mapper;

    @Override
    public CreateRouteDto entityToDto(Route route) {
        return mapper.map(route, CreateRouteDto.class);
    }

    @Override
    public Route dtoToEntity(CreateRouteDto createRouteDto) {
        return mapper.map(createRouteDto, Route.class);
    }
}
