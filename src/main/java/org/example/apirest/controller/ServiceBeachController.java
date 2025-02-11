package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.service.ServiceBeachDto;
import org.example.apirest.dto.service.CreateServiceBeachDto;
import org.example.apirest.service.service.ServiceBeachServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ServiceBeachController {

    private final ServiceBeachServiceImpl service;

    @GetMapping
    public ResponseEntity<List<ServiceBeachDto>> index() {
        List<ServiceBeachDto> services = service.findAll();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceBeachDto> show(@PathVariable Long id) {
        ServiceBeachDto serviceBeach = service.findOne(id);
        return ResponseEntity.ok(serviceBeach);
    }

    @PostMapping
    public ResponseEntity<ServiceBeachDto> create(@RequestBody CreateServiceBeachDto entity) {
        ServiceBeachDto newServiceBeach = service.save(entity);
        return ResponseEntity.ok(newServiceBeach);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceBeachDto> update(@PathVariable Long id, @RequestBody CreateServiceBeachDto entity) {
        ServiceBeachDto updatedServiceBeach = service.update(id, entity);
        return ResponseEntity.ok(updatedServiceBeach);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
