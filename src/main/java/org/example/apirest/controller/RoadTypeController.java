package org.example.apirest.controller;

import org.example.apirest.dto.roadType.CreateRoadTypeDto;
import org.example.apirest.dto.roadType.RoadTypeDto;
import org.example.apirest.service.roadType.RoadTypeServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/road-types")
@CrossOrigin(origins = "*")
public class RoadTypeController extends GeneralizedController<RoadTypeDto, CreateRoadTypeDto> {
    public RoadTypeController(RoadTypeServiceImpl service) {
        super(service);
    }
}
