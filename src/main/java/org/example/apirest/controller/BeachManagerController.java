package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.beachManager.BeachManagerDto;
import org.example.apirest.dto.beachManager.CreateBeachManagerDto;
import org.example.apirest.service.beachHasService.BeachHasServiceServiceImpl;
import org.example.apirest.service.beachManager.BeachManagerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/beach-manager")
@CrossOrigin(origins = "*")
public class BeachManagerController extends GeneralizedController<BeachManagerDto, CreateBeachManagerDto> {
    public BeachManagerController(BeachManagerServiceImpl service) {
        super(service);
    }
}
