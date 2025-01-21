package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.location.LocationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "*")
public class LocationController extends GeneralizedController<LocationDto, CreateLocationDto> {
    public LocationController(LocationServiceImpl service) {
        super(service);
    }
}
