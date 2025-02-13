package org.example.apirest.service.location;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.model.Location;
import org.example.apirest.repository.LocationRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.service.photo.PhotoServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl extends GeneralizedServiceImpl<Location, LocationDto, CreateLocationDto, LocationRepository> {

    private final PhotoServiceImpl photoService;

    public LocationServiceImpl(LocationRepository repository,
                               DtoConverterGeneralizedImpl<Location,LocationDto,CreateLocationDto> dtoConverter,
                               PhotoServiceImpl photoService) {
        super(repository, dtoConverter, Location.class, LocationDto.class);
        this.photoService = photoService;
    }

    //    @Override
//    public LocationDto save(CreateLocationDto createLocationDto){
//        Location location = dtoConverter.convertToEntityFromCreateDto(createLocationDto,Location.class);
//        this.repository.save(location);
//
//    }
}
