package org.example.apirest.controller;


import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/beaches")
@CrossOrigin(origins = "*")
public class BeachController extends GeneralizedControllerImpl<BeachDto, CreateBeachDto> {
    public BeachController(BeachServiceImpl service) {
        super(service);
    }
}
