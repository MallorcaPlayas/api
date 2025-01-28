package org.example.apirest.service.route;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.role.CreateRoleDto;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Location;
import org.example.apirest.model.Role;
import org.example.apirest.model.Route;
import org.example.apirest.repository.LocationRepository;
import org.example.apirest.repository.RoleRepository;
import org.example.apirest.repository.RouteRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl extends GeneralizedServiceImpl<Route, RouteDto, CreateRouteDto, RouteRepository> {

    private final DtoConverterImpl<Location, LocationDto,CreateLocationDto> dtoConverterLocation;

    public RouteServiceImpl(RouteRepository repository,
                            DtoConverterImpl<Route,RouteDto,CreateRouteDto> dtoConverter,
                            LocationRepository locationRepository,
                            DtoConverterImpl<Location, LocationDto, CreateLocationDto> dtoConverterLocation) {

        super(repository, dtoConverter, Route.class, RouteDto.class);
        this.dtoConverterLocation = dtoConverterLocation;
    }

    @Override
    public RouteDto save(CreateRouteDto entity) {
        Route route = dtoConverter.convertToEntityFromCreateDto(entity, Route.class);
        List<Location> locations = dtoConverterLocation.convertToEntityListFromCreateDto(entity.getLocations(),Location.class);
        System.out.println(locations);
        for(Location location : locations){
           location.setRoute(route);
        }
        route.setLocations(locations);
        return dtoConverter.convertDto(repository.save(route),RouteDto.class);
    }
}
