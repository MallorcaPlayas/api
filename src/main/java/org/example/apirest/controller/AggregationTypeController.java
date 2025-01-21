package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.aggregationType.AggregationTypeDto;
import org.example.apirest.dto.aggregationType.CreateAggregationTypeDto;
import org.example.apirest.service.GeneralizedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/aggregation-types")
@CrossOrigin(origins = "*")
public class AggregationTypeController extends GeneralizedController<AggregationTypeDto, CreateAggregationTypeDto> {
    public AggregationTypeController(GeneralizedService<AggregationTypeDto, CreateAggregationTypeDto> service) {
        super(service);
    }
}
