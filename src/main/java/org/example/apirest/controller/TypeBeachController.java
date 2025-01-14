package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.service.typeBeach.TypeBeachService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/types")
@RequiredArgsConstructor
public class TypeBeachController {

    private final TypeBeachService service;

    @GetMapping
    public ResponseEntity<List<TypeBeachDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeBeachDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<TypeBeachDto> create(@RequestBody CreateTypeBeachDto beach) {
        TypeBeachDto newTypeBeach = service.save(beach);
        return ResponseEntity.created(URI.create("/api/playas/" + newTypeBeach.getId())).body(newTypeBeach);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeBeachDto> update(@RequestBody CreateTypeBeachDto beach,@PathVariable Long id) {
        TypeBeachDto updatedTypeBeach = service.update(id,beach);
        return ResponseEntity.created(URI.create("/api/playas/" + id)).body(updatedTypeBeach);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
