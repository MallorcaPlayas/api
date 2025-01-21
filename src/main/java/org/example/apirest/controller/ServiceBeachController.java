package org.example.apirest.controller;


import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.service.CreateServiceBeachDto;
import org.example.apirest.dto.service.ServiceBeachDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.service.ServiceBeachServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
public class ServiceBeachController extends GeneralizedController<ServiceBeachDto, CreateServiceBeachDto> {
    public ServiceBeachController(ServiceBeachServiceImpl service) {
        super(service);
    }
}
