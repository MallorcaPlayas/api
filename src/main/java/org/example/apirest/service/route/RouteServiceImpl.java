package org.example.apirest.service.route;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.location.Location;
import org.example.apirest.model.Photo;
import org.example.apirest.model.route.Route;
import org.example.apirest.service.location.LocationServiceImpl;
import org.example.apirest.utils.RouteHandler;
import org.example.apirest.utils.Utils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.parsers.SAXParser;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl {

    private final DtoConverter<Route, RouteDto> routeDtoConverter;
    private final DtoConverter<Route, CreateRouteDto> createRouteDtoConverter;

    private final JpaRepository<Route,Long> repository;
    private final SAXParser saxParser;
    private final LocationServiceImpl locationServiceImpl;

    public RouteDto findOne(Long id){
        Route route = repository.findById(id).orElseThrow(() -> new NotFoundException(Route.class , id));
        return routeDtoConverter.entityToDto(route);
    }

    public List<RouteDto> findAll(){
        return routeDtoConverter.entityListToDtoList(repository.findAll());
    }

    @SneakyThrows
    public RouteDto save(CreateRouteDto entity) {
        Route route = repository.save(createRouteDtoConverter.dtoToEntity(entity));
        return routeDtoConverter.entityToDto(repository.save(route));
    }

    @SneakyThrows
    public RouteDto update(Long id , CreateRouteDto entity) {
        // get location from database
        Route routeOld = repository.findById(id).orElseThrow(() -> new NotFoundException(Route.class , id));

        // if not exit return
        if (routeOld == null) {
            return null;
        }

        // convert from dto to route
        Route routeNew = createRouteDtoConverter.dtoToEntity(entity);

        // updating fields
        Utils.updateFields(routeOld, routeNew);

        return routeDtoConverter.entityToDto(repository.save(routeOld));
    }

    @SneakyThrows
    public RouteDto upload(MultipartFile multipartFile,CreateRouteDto createRouteDto) {
        RouteHandler routeHandler = new RouteHandler();

        saxParser.parse(multipartFile.getInputStream(),routeHandler);

        Route route = repository.save(createRouteDtoConverter.dtoToEntity(createRouteDto));

        routeHandler.getWayLocations().forEach(location -> {
            locationServiceImpl.createInRoute(location,route.getId());
        });

        return routeDtoConverter.entityToDto(route);
    }

    @SneakyThrows
    @Transactional
    public RouteDto upload(MultipartFile multipartFile){
        RouteHandler routeHandler = new RouteHandler();

        saxParser.parse(multipartFile.getInputStream(),routeHandler);
        CreateRouteDto createRouteDto = routeHandler.getRoute();

        Route route = repository.save(createRouteDtoConverter.dtoToEntity(createRouteDto));

        routeHandler.getWayLocations().forEach(location -> {
            locationServiceImpl.createInRoute(location,route.getId());
        });

        return routeDtoConverter.entityToDto(repository.save(route));
    }

    @SneakyThrows
    @Transactional
    public void delete(Long id) {
        Route entity = repository.findById(id).orElseThrow(()-> new NotFoundException(Route.class,id));

        locationServiceImpl.deleteAllFromRoute(id);

        repository.delete(entity);
    }
}
