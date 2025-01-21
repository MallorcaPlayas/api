package org.example.apirest.service.location;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.horary.CreateHoraryDto;
import org.example.apirest.dto.horary.HoraryDto;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Horary;
import org.example.apirest.model.Location;
import org.example.apirest.repository.HoraryRepository;
import org.example.apirest.repository.LocationRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl extends GeneralizedServiceImpl<Location, LocationDto, CreateLocationDto, LocationRepository> {
    public LocationServiceImpl(LocationRepository repository, DtoConverterImpl<Location,LocationDto,CreateLocationDto> dtoConverter) {
        super(repository, dtoConverter, Location.class, LocationDto.class);
    }
}
