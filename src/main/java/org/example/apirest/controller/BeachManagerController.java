package org.example.apirest.controller;

import org.example.apirest.dto.beachManager.BeachManagerDto;
import org.example.apirest.dto.beachManager.CreateBeachManagerDto;
import org.example.apirest.service.beachManager.BeachManagerServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/beach-manager")
@CrossOrigin(origins = "*")
public class BeachManagerController extends GeneralizedControllerImpl<BeachManagerDto, CreateBeachManagerDto> {
    public BeachManagerController(BeachManagerServiceImpl service) {
        super(service);
    }
}
