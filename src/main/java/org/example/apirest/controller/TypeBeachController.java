package org.example.apirest.controller;

import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.service.typeBeach.TypeBeachServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/types")
@CrossOrigin(origins = "*")
public class TypeBeachController extends GeneralizedControllerImpl<TypeBeachDto, CreateTypeBeachDto> {
    public TypeBeachController(TypeBeachServiceImpl service) {
        super(service);
    }
}
