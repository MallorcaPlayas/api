package org.example.apirest.service.location;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Location;
import org.example.apirest.repository.LocationRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository repository;
    private final DtoConverterImpl<Location, LocationDto, CreateLocationDto> dtoConverter;

    @Override
    public List<LocationDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), LocationDto.class);
    }

    @Override
    public LocationDto findOne(Long id) {
        Location location = repository.findById(id).orElseThrow(() -> new NotFoundException(Location.class, id));
        return dtoConverter.convertDto(location, LocationDto.class);
    }

    @Override
    public LocationDto save(CreateLocationDto location) {
        Location locationToInsert = dtoConverter.convertToEntityFromCreateDto(location, Location.class);
        return dtoConverter.convertDto(repository.save(locationToInsert), LocationDto.class);
    }

    @Override
    public LocationDto update(Long id, CreateLocationDto location) {
        Location oldLocation = repository.findById(id).orElseThrow(() -> new NotFoundException(Location.class, id));
        Location locationToInsert = dtoConverter.convertToEntityFromCreateDto(location, Location.class);

        if (oldLocation == null) {
            return null;
        }

        UtilsClass.updateFields(oldLocation, locationToInsert);

        return dtoConverter.convertDto(repository.save(oldLocation), LocationDto.class);
    }

    @Override
    public void delete(Long id) {
        Location location = repository.findById(id).orElseThrow(() -> new NotFoundException(Location.class, id));
        repository.delete(location);
    }
}
