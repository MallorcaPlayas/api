package org.example.apirest.controller;

import org.example.apirest.dto.excursion.ExcursionDto;
import org.example.apirest.dto.excursion.CreateExcursionDto;
import org.example.apirest.service.excursion.ExcursionServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/excursions")
@CrossOrigin(origins = "*")
public class ExcursionController extends GeneralizedController<ExcursionDto, CreateExcursionDto> {
    public ExcursionController(ExcursionServiceImpl service) {
        super(service);
    }
}
