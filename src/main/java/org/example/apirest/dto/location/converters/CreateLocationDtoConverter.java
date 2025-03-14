package org.example.apirest.dto.location.converters;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.model.location.Location;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateLocationDtoConverter implements DtoConverter<Location, CreateLocationDto> {

    private final ModelMapper mapper;

    @Override
    public CreateLocationDto entityToDto(Location location) {
        return mapper.map(location, CreateLocationDto.class);
    }

    @Override
    public Location dtoToEntity(CreateLocationDto createLocationDto) {
        return mapper.map(createLocationDto, Location.class);
    }
}
