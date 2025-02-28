package org.example.apirest.dto.route.converters;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.model.location.Location;
import org.example.apirest.model.route.Route;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateRouteDtoConverter implements DtoConverter<Route, CreateRouteDto> {

    private final DtoConverter<Location, CreateLocationDto> createLocationDtoConverter;

    private final ModelMapper mapper;

    @Override
    public CreateRouteDto entityToDto(Route route) {
        List<CreateLocationDto> locations = createLocationDtoConverter.entityListToDtoList(route.getLocations());
        CreateRouteDto createRouteDto = mapper.map(route, CreateRouteDto.class);
        createRouteDto.setLocations(locations);
        return createRouteDto;
    }

    @Override
    public Route dtoToEntity(CreateRouteDto createRouteDto) {
        List<Location> locations = createLocationDtoConverter.dtoListToEntityList(createRouteDto.getLocations());
        Route route = mapper.map(createRouteDto, Route.class);
        route.setLocations(locations);
        return route;
    }
}
