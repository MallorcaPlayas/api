package org.example.apirest.controller;

import org.example.apirest.dto.aggregationType.AggregationTypeDto;
import org.example.apirest.dto.aggregationType.CreateAggregationTypeDto;
import org.example.apirest.service.aggregationType.AggregationTypeServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aggregation-types")
@CrossOrigin(origins = "*")
public class AggregationTypeController extends GeneralizedController<AggregationTypeDto, CreateAggregationTypeDto> {
    public AggregationTypeController(AggregationTypeServiceImpl service) {
        super(service);
    }
}
