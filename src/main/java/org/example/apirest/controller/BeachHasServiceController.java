package org.example.apirest.controller;


import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.beachHasService.BeachHasServiceDto;
import org.example.apirest.dto.beachHasService.CreateBeachHasServiceDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.beachHasService.BeachHasServiceServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/beach-has-service")
@CrossOrigin(origins = "*")
public class BeachHasServiceController  extends GeneralizedController<BeachHasServiceDto, CreateBeachHasServiceDto> {
    public BeachHasServiceController(BeachHasServiceServiceImpl service) {
        super(service);
    }
}
