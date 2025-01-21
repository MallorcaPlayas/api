package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.route.RouteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "*")
public class RouteController extends GeneralizedController<RouteDto, CreateRouteDto> {
    public RouteController(RouteServiceImpl service) {
        super(service);
    }
}
