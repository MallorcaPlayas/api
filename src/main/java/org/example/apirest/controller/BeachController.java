package org.example.apirest.controller;


import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.service.CreateServiceDto;
import org.example.apirest.dto.service.ServiceDto;
import org.example.apirest.model.ServiceBeach;
import org.example.apirest.service.beach.BeachService;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.service.ServiceBeachServiceImpl;
import org.example.apirest.service.typeBeach.TypeBeachService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/playa")
@RequiredArgsConstructor
public class BeachController {

    private final BeachService service;

    @GetMapping
    public ResponseEntity<List<BeachDto>> findAllRestaurants() {
        return ResponseEntity.ok(service.findAll());
    }

}
