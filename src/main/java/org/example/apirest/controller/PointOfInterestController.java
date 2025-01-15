package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.pointOfInterest.PointOfInterestDto;
import org.example.apirest.dto.pointOfInterest.CreatePointOfInterestDto;
import org.example.apirest.service.pointOfInterest.PointOfInterestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/points-of-interest")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PointOfInterestController {

    private final PointOfInterestService service;

    @GetMapping
    public ResponseEntity<List<PointOfInterestDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PointOfInterestDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<PointOfInterestDto> create(@RequestBody CreatePointOfInterestDto pointOfInterest) {
        PointOfInterestDto newPointOfInterest = service.save(pointOfInterest);
        return ResponseEntity.created(URI.create("/api/points-of-interest/" + newPointOfInterest.getId())).body(newPointOfInterest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PointOfInterestDto> update(@RequestBody CreatePointOfInterestDto pointOfInterest, @PathVariable Long id) {
        PointOfInterestDto updatedPointOfInterest = service.update(id, pointOfInterest);
        return ResponseEntity.created(URI.create("/api/points-of-interest/" + id)).body(updatedPointOfInterest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
