package org.example.apirest.service.location;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Location;
import org.example.apirest.repository.LocationRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements DtoConverter<Location, LocationDto, CreateLocationDto> {

    private final LocationRepository repository;
    private final ModelMapper mapper;

    public List<LocationDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public LocationDto findOne(Long id) {
        Location location = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Location.class, id));
        return this.toDto(location);
    }

    public LocationDto save(CreateLocationDto createLocationDto) {
        Location location = fromDto(createLocationDto);
        Location savedLocation = repository.save(location);
        return toDto(savedLocation);
    }

    public LocationDto update(Long id, CreateLocationDto createLocationDto) {
        Location oldLocation = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Location.class, id));
        Location locationToUpdate = fromDto(createLocationDto);

        UtilsClass.updateFields(oldLocation, locationToUpdate);

        Location savedLocation = repository.save(oldLocation);
        return toDto(savedLocation);
    }

    public void delete(Long id) {
        Location location = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Location.class, id));
        repository.delete(location);
    }

    @Override
    public LocationDto toDto(Location location) {
        return mapper.map(location, LocationDto.class);
    }

    @Override
    public List<LocationDto> toDtoList(List<Location> locations) {
        return locations.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public Location fromDto(CreateLocationDto createLocationDto) {
        return mapper.map(createLocationDto, Location.class);
    }

    @Override
    public List<Location> fromDtoList(List<CreateLocationDto> createLocationDtos) {
        return createLocationDtos.stream()
                .map(this::fromDto)
                .toList();
    }
}
