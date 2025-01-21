package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.horary.HoraryDto;
import org.example.apirest.dto.horary.CreateHoraryDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.horary.HoraryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/horaries")
@CrossOrigin(origins = "*")
public class HoraryController extends GeneralizedController<HoraryDto, CreateHoraryDto> {
    public HoraryController(HoraryServiceImpl service) {
        super(service);
    }
}
