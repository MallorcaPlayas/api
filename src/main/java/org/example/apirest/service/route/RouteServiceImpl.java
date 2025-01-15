package org.example.apirest.service.route;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Route;
import org.example.apirest.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository repository;
    private final DtoConverterImpl<Route, RouteDto, CreateRouteDto> dtoConverter;

    @Override
    public List<RouteDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), RouteDto.class);
    }

    @Override
    public RouteDto findOne(Long id) {
        Route route = repository.findById(id).orElseThrow(() -> new NotFoundException(Route.class, id));
        return dtoConverter.convertDto(route, RouteDto.class);
    }

    @Override
    public RouteDto save(CreateRouteDto route) {
        Route routeToInsert = dtoConverter.convertToEntityFromCreateDto(route, Route.class);
        return dtoConverter.convertDto(repository.save(routeToInsert), RouteDto.class);
    }

    @Override
    public RouteDto update(Long id, CreateRouteDto route) {
        Route oldRoute = repository.findById(id).orElseThrow(() -> new NotFoundException(Route.class, id));
        Route routeToInsert = dtoConverter.convertToEntityFromCreateDto(route, Route.class);

        if (oldRoute == null) {
            return null;
        }

        oldRoute.setName(routeToInsert.getName());
        oldRoute.setPrivate(routeToInsert.isPrivate());
        oldRoute.setDistance(routeToInsert.getDistance());
        oldRoute.setDuration(routeToInsert.getDuration());
        oldRoute.setElevation(routeToInsert.getElevation());

        return dtoConverter.convertDto(repository.save(oldRoute), RouteDto.class);
    }

    @Override
    public void delete(Long id) {
        Route route = repository.findById(id).orElseThrow(() -> new NotFoundException(Route.class, id));
        repository.delete(route);
    }
}
