package org.example.apirest.service.location;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.location.Location;
import org.example.apirest.repository.location.LocationRepositoryFirestore;
import org.example.apirest.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl {

    private final LocationRepositoryFirestore repository;
    private final DtoConverter<Location, LocationDto> locationDtoConverter;
    private final DtoConverter<Location, CreateLocationDto> createLocationDtoConverter;

    public List<LocationDto> findAllInRoute(Long routeId) {
        return locationDtoConverter.entityListToDtoList(repository.findAllByRouteId(routeId));
    }

    public LocationDto createInRoute(CreateLocationDto createLocationDto , Long id) {
        Location location = createLocationDtoConverter.dtoToEntity(createLocationDto);
        return locationDtoConverter.entityToDto(repository.saveInRoute(location , id));
    }

//    public LocationDto updateInRoute(CreateLocationDto createLocationDto , Long id) {
//        Location location = createLocationDtoConverter.dtoToEntity(createLocationDto);
//        return locationDtoConverter.entityToDto(repository.saveInRoute(location , id));
//    }

    public void deleteFromRoute(Long locationId, Long routeId) {
        Location location = repository.findByIdInRoute(locationId , routeId);
        repository.deleteFromRoute(location , routeId);
    }
}
