package org.example.apirest.controller;


import org.example.apirest.dto.service.CreateServiceBeachDto;
import org.example.apirest.dto.service.ServiceBeachDto;
import org.example.apirest.service.service.ServiceBeachServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
public class ServiceBeachController extends GeneralizedController<ServiceBeachDto, CreateServiceBeachDto> {
    public ServiceBeachController(ServiceBeachServiceImpl service) {
        super(service);
    }
}
