package org.example.apirest.controller;

import org.example.apirest.dto.pointOfInterest.PointOfInterestDto;
import org.example.apirest.dto.pointOfInterest.CreatePointOfInterestDto;
import org.example.apirest.service.pointOfInterest.PointOfInterestServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/points-of-interest")
@CrossOrigin(origins = "*")
public class PointOfInterestController{
    protected final GeneralizedService<Dto,CreateDto> service;

    @GetMapping
    public ResponseEntity<List<Dto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<Dto> create(@RequestBody CreateDto entity) {
        Dto newEntity = service.save(entity);
        return ResponseEntity.ok(newEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dto> update(@RequestBody CreateDto entity, @PathVariable Long id) {
        Dto updated = service.update(id, entity);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
