package org.example.apirest.dto.location.converters;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.model.location.Location;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
@RequiredArgsConstructor
public class LocationDtoConverter implements DtoConverter<Location, LocationDto> {

    private final ModelMapper mapper;

    @Override
    public LocationDto entityToDto(Location location) {
        // converting basic data to location DTO
        LocationDto locationDto = mapper.map(location, LocationDto.class);

        // partsing manually all the complex data to necessary data
        locationDto.setLongitude(location.getPoint().getLongitude());
        locationDto.setLatitude(location.getPoint().getLatitude());

        if( location.getTime() != null ) {
            locationDto.setTime(
                    location.getTime()
                            .toDate()
                            .toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime()
            );
        }


        return locationDto;
    }

    @Override
    public Location dtoToEntity(LocationDto locationDto) {
        return mapper.map(locationDto, Location.class);
    }
}
