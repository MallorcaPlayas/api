package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RouteController {

    private final RouteService service;

    @GetMapping
    public ResponseEntity<List<RouteDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<RouteDto> create(@RequestBody CreateRouteDto route) {
        RouteDto newRoute = service.save(route);
        return ResponseEntity.created(URI.create("/api/routes/" + newRoute.getId())).body(newRoute);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RouteDto> update(@RequestBody CreateRouteDto route, @PathVariable Long id) {
        RouteDto updatedRoute = service.update(id, route);
        return ResponseEntity.created(URI.create("/api/routes/" + id)).body(updatedRoute);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
