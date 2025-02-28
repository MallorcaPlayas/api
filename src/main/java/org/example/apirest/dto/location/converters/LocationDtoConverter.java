package org.example.apirest.dto.location.converters;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.model.location.Location;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationDtoConverter implements DtoConverter<Location, LocationDto> {

    private final ModelMapper mapper;

    @Override
    public LocationDto entityToDto(Location location) {
        return mapper.map(location, LocationDto.class);
    }

    @Override
    public Location dtoToEntity(LocationDto locationDto) {
        return mapper.map(locationDto, Location.class);
    }
}
