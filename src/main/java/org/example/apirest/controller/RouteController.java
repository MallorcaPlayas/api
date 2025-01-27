package org.example.apirest.controller;

import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.service.route.RouteServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "*")
public class RouteController extends GeneralizedController<RouteDto, CreateRouteDto> {
    public RouteController(RouteServiceImpl service) {
        super(service);
    }
}
