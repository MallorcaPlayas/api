package org.example.apirest.controller;

import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.service.location.LocationServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "*")
public class LocationController extends GeneralizedControllerImpl<LocationDto, CreateLocationDto> {
    public LocationController(LocationServiceImpl service) {
        super(service);
    }
}
