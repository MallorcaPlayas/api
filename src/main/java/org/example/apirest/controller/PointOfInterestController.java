package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.pointOfInterest.PointOfInterestDto;
import org.example.apirest.dto.pointOfInterest.CreatePointOfInterestDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.pointOfInterest.PointOfInterestServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/points-of-interest")
@CrossOrigin(origins = "*")
public class PointOfInterestController extends GeneralizedController<PointOfInterestDto, CreatePointOfInterestDto> {
    public PointOfInterestController(PointOfInterestServiceImpl service) {
        super(service);
    }
}
