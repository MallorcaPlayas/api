package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.typeBeach.TypeBeachServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/types")
@CrossOrigin(origins = "*")
public class TypeBeachController extends GeneralizedController<TypeBeachDto, CreateTypeBeachDto> {
    public TypeBeachController(TypeBeachServiceImpl service) {
        super(service);
    }
}
