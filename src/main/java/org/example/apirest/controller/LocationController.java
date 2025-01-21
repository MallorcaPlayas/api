package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.location.CreateLocationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LocationController {

    private final LocationService service;

    @GetMapping
    public ResponseEntity<List<LocationDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<LocationDto> create(@RequestBody CreateLocationDto location) {
        LocationDto newLocation = service.save(location);
        return ResponseEntity.created(URI.create("/api/locations/" + newLocation.getId())).body(newLocation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationDto> update(@RequestBody CreateLocationDto location, @PathVariable Long id) {
        LocationDto updatedLocation = service.update(id, location);
        return ResponseEntity.created(URI.create("/api/locations/" + id)).body(updatedLocation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
