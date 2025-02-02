package org.example.apirest.controller;

import org.example.apirest.dto.pointOfInterestType.PointOfInterestTypeDto;
import org.example.apirest.dto.pointOfInterestType.CreatePointOfInterestTypeDto;
import org.example.apirest.service.pointOfInterestType.PointOfInterestTypeServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/point-of-interest-types")
@CrossOrigin(origins = "*")
public class PointOfInterestTypeController extends GeneralizedController<PointOfInterestTypeDto, CreatePointOfInterestTypeDto> {
    public PointOfInterestTypeController(PointOfInterestTypeServiceImpl service) {
        super(service);
    }
}
