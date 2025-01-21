package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.pointOfInterestType.PointOfInterestTypeDto;
import org.example.apirest.dto.pointOfInterestType.CreatePointOfInterestTypeDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.pointOfInterestType.PointOfInterestTypeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/point-of-interest-types")
@CrossOrigin(origins = "*")
public class PointOfInterestTypeController extends GeneralizedController<PointOfInterestTypeDto, CreatePointOfInterestTypeDto> {
    public PointOfInterestTypeController(PointOfInterestTypeServiceImpl service) {
        super(service);
    }
}
