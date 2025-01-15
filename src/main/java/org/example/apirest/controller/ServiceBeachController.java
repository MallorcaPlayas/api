package org.example.apirest.controller;


import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.service.CreateServiceBeachDto;
import org.example.apirest.dto.service.ServiceBeachDto;
import org.example.apirest.service.service.ServiceBeachService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ServiceBeachController {

    private final ServiceBeachService service;

    @GetMapping
    public ResponseEntity<List<ServiceBeachDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceBeachDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<ServiceBeachDto> create(@RequestBody CreateServiceBeachDto serviceBeach) {
        ServiceBeachDto newBeach = service.save(serviceBeach);
        return ResponseEntity.created(URI.create("/api/playas/" + newBeach.getId())).body(newBeach);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceBeachDto> update(@RequestBody CreateServiceBeachDto serviceBeach,@PathVariable Long id) {
        ServiceBeachDto updatedServiceBeach = service.update(id,serviceBeach);
        return ResponseEntity.created(URI.create("/api/playas/" + id)).body(updatedServiceBeach);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
