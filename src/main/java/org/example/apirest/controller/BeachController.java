package org.example.apirest.controller;


import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.aggregationType.AggregationTypeDto;
import org.example.apirest.dto.aggregationType.CreateAggregationTypeDto;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.model.Beach;
import org.example.apirest.service.GeneralizedService;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/beaches")
@CrossOrigin(origins = "*")
public class BeachController extends GeneralizedController<BeachDto, CreateBeachDto> {
    public BeachController(BeachServiceImpl service) {
        super(service);
    }
}
