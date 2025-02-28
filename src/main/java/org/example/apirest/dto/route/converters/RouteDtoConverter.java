package org.example.apirest.dto.route.converters;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.model.route.Route;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RouteDtoConverter implements DtoConverter<Route, RouteDto> {

    private final ModelMapper mapper;

    @Override
    public RouteDto entityToDto(Route route) {
        return mapper.map(route, RouteDto.class);
    }

    @Override
    public Route dtoToEntity(RouteDto routeDto) {
        return mapper.map(routeDto, Route.class);
    }
}
