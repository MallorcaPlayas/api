package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.excursion.ExcursionDto;
import org.example.apirest.dto.excursion.CreateExcursionDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.excursion.ExcursionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/excursions")
@CrossOrigin(origins = "*")
public class ExcursionController extends GeneralizedController<ExcursionDto, CreateExcursionDto> {
    public ExcursionController(ExcursionServiceImpl service) {
        super(service);
    }
}
