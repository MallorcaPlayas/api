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
import org.example.apirest.utils.RouteHandler;
import org.example.apirest.utils.Utils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.parsers.SAXParser;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl {

    private final DtoConverter<Location,LocationDto> locationDtoConverter;
    private final DtoConverter<Location,CreateLocationDto> createLocationDtoDtoConverter;
    private final DtoConverter<Photo, PhotoDto> photoDtoConverter;
    private final DtoConverter<Route, RouteDto> routeDtoConverter;
    private final DtoConverter<Route, CreateRouteDto> createRouteDtoConverter;

    private final JpaRepository<Route,Long> repository;
    private final SAXParser saxParser;

    public RouteDto findOne(Long id){

        Route route = repository.findById(id).orElseThrow(() -> new NotFoundException(Route.class , id));

        RouteDto routeDto = routeDtoConverter.entityToDto(route);

        List<PhotoDto> photoDtos = photoDtoConverter.entityListToDtoList(route.getPhotos());

        routeDto.setPhotos(photoDtos);

        return routeDto;
    }

    public List<RouteDto> findAll(){

        List<Route> routes = repository.findAll();

        return routes.stream()
                .map(route -> {
                    RouteDto routeDto = routeDtoConverter.entityToDto(route);

                    List<PhotoDto> photoDtos = photoDtoConverter.entityListToDtoList(route.getPhotos());

                    routeDto.setPhotos(photoDtos);

                    return routeDto;
                    
                })
                .toList();
    }

    public RouteDto save(CreateRouteDto entity) {
        Route route = createRouteDtoConverter.dtoToEntity(entity);
        List<Location> locations = createLocationDtoDtoConverter.dtoListToEntityList(entity.getLocations());
        System.out.println(locations);
        for(Location location : locations){
           location.setRoute(route);
        }
        route.setLocations(locations);
        return routeDtoConverter.entityToDto(repository.save(route));
    }

    @SneakyThrows
    public RouteDto upload(MultipartFile multipartFile){
        RouteHandler routeHandler = new RouteHandler();

        saxParser.parse(multipartFile.getInputStream(),routeHandler);
        CreateRouteDto createRouteDto = routeHandler.getRoute();

        Route route = createRouteDtoConverter.dtoToEntity(createRouteDto);

        List<Location> locations = createLocationDtoDtoConverter.dtoListToEntityList(createRouteDto.getLocations());

        for(Location location : locations){
            location.setRoute(route);
        }

        route.setLocations(locations);
        return routeDtoConverter.entityToDto(repository.save(route));
    }

    @SneakyThrows
    public List<RouteDto> uploadList(List<MultipartFile> files) {
        return files.stream().map(this::upload).toList();
    }

    public RouteDto update(Long id, CreateRouteDto createEntity) {
        Route oldEntity = repository.findById(id).orElseThrow(() -> new NotFoundException(Route.class, id));
        Route entityToInsert = createRouteDtoConverter.dtoToEntity(createEntity);

        if (oldEntity == null) {
            return null;
        }

        Utils.updateFields(oldEntity, entityToInsert);

        return routeDtoConverter.entityToDto(repository.save(oldEntity));
    }

    public void delete(Long id) {
        Route entity = repository.findById(id).orElseThrow(()-> new NotFoundException(Route.class,id));
        repository.delete(entity);
    }
}
