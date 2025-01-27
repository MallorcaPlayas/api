package org.example.apirest.controller;

import org.example.apirest.dto.pointOfInterest.PointOfInterestDto;
import org.example.apirest.dto.pointOfInterest.CreatePointOfInterestDto;
import org.example.apirest.service.pointOfInterest.PointOfInterestServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/points-of-interest")
@CrossOrigin(origins = "*")
public class PointOfInterestController extends GeneralizedControllerImpl<PointOfInterestDto, CreatePointOfInterestDto> {
    public PointOfInterestController(PointOfInterestServiceImpl service) {
        super(service);
    }
}
