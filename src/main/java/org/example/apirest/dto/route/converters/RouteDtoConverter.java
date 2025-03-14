package org.example.apirest.dto.route.converters;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.photo.converters.PhotoDtoConverter;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.model.Photo;
import org.example.apirest.model.location.Location;
import org.example.apirest.model.route.Route;
import org.example.apirest.repository.location.LocationRepositoryFirestore;
import org.example.apirest.service.location.LocationServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RouteDtoConverter implements DtoConverter<Route, RouteDto> {

    private final ModelMapper mapper;
    private final LocationRepositoryFirestore locationRepository;

    private final DtoConverter<Photo, PhotoDto> photoDtoConverter;
    private final DtoConverter<Location, LocationDto> locationDtoConverter;

    @Override
    public RouteDto entityToDto(Route route) {
        // convert route to dto
        RouteDto routeDto = mapper.map(route, RouteDto.class);
        // convert photos to dto
        if(route.getPhotos() != null ){
            List<PhotoDto> photoDtos = photoDtoConverter.entityListToDtoList(route.getPhotos());
            routeDto.setPhotos(photoDtos);
        }
        // get locations of the route
        List<Location> locations = locationRepository.findAllByRouteId(route.getId());
        // convert locations to the dto
        List<LocationDto> locationDtos = locationDtoConverter.entityListToDtoList(locations);

        // set location and route to the dto
        routeDto.setLocations(locationDtos);

        return routeDto;
    }

    @Override
    public Route dtoToEntity(RouteDto routeDto) {
        return mapper.map(routeDto, Route.class);
    }
}
