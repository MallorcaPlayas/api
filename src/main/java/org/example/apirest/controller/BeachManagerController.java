package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beachManager.BeachManagerDto;
import org.example.apirest.dto.beachManager.CreateBeachManagerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/beach-manager")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BeachManagerController {
    private final BeachManagerService service;

    @GetMapping
    public ResponseEntity<List<BeachManagerDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeachManagerDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<BeachManagerDto> create(@RequestBody CreateBeachManagerDto entity) {
        BeachManagerDto newEntity = service.save(entity);
        return ResponseEntity.created(URI.create("/api/playas/" + newEntity.getId())).body(newEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeachManagerDto> update(@RequestBody CreateBeachManagerDto entity,@PathVariable Long id) {
        BeachManagerDto updatedEntity = service.update(id,entity);
        return ResponseEntity.created(URI.create("/api/playas/" + id)).body(updatedEntity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
