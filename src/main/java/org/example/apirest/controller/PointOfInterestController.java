package org.example.apirest.controller;

import org.example.apirest.dto.pointOfInterest.PointOfInterestDto;
import org.example.apirest.dto.pointOfInterest.CreatePointOfInterestDto;
import org.example.apirest.service.pointOfInterest.PointOfInterestServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/points-of-interest")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PointOfInterestController {

    private final PointOfInterestServiceImpl service;

    @GetMapping
    public ResponseEntity<List<PointOfInterestDto>> index() {
        List<PointOfInterestDto> pointsOfInterest = service.findAll();
        return ResponseEntity.ok(pointsOfInterest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PointOfInterestDto> show(@PathVariable Long id) {
        PointOfInterestDto pointOfInterest = service.findOne(id);
        return ResponseEntity.ok(pointOfInterest);
    }

    @PostMapping
    public ResponseEntity<PointOfInterestDto> create(@RequestBody CreatePointOfInterestDto dto) {
        PointOfInterestDto newPointOfInterest = service.save(dto);
        return ResponseEntity.ok(newPointOfInterest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PointOfInterestDto> update(@PathVariable Long id, @RequestBody CreatePointOfInterestDto dto) {
        PointOfInterestDto updatedPointOfInterest = service.update(id, dto);
        return ResponseEntity.ok(updatedPointOfInterest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
