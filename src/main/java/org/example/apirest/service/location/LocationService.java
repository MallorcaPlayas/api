package org.example.apirest.service.location;

import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.location.CreateLocationDto;

import java.util.List;

public interface LocationService {
    List<LocationDto> findAll();
    LocationDto findOne(Long id);
    LocationDto save(CreateLocationDto location);
    LocationDto update(Long id, CreateLocationDto location);
    void delete(Long id);
}
