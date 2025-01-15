package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.pointOfInterestType.PointOfInterestTypeDto;
import org.example.apirest.dto.pointOfInterestType.CreatePointOfInterestTypeDto;
import org.example.apirest.service.pointOfInterestType.PointOfInterestTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/point-of-interest-types")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PointOfInterestTypeController {

    private final PointOfInterestTypeService service;

    @GetMapping
    public ResponseEntity<List<PointOfInterestTypeDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PointOfInterestTypeDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<PointOfInterestTypeDto> create(@RequestBody CreatePointOfInterestTypeDto pointOfInterestType) {
        PointOfInterestTypeDto newPointOfInterestType = service.save(pointOfInterestType);
        return ResponseEntity.created(URI.create("/api/point-of-interest-types/" + newPointOfInterestType.getId())).body(newPointOfInterestType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PointOfInterestTypeDto> update(@RequestBody CreatePointOfInterestTypeDto pointOfInterestType, @PathVariable Long id) {
        PointOfInterestTypeDto updatedPointOfInterestType = service.update(id, pointOfInterestType);
        return ResponseEntity.created(URI.create("/api/point-of-interest-types/" + id)).body(updatedPointOfInterestType);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
