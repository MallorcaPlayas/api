package org.example.apirest.controller;


import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beachHasService.BeachHasServiceDto;
import org.example.apirest.dto.beachHasService.CreateBeachHasServiceDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/beach-has-service")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BeachHasServiceController {

    private final BeachHasServiceService service;

    @GetMapping
    public ResponseEntity<List<BeachHasServiceDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeachHasServiceDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<BeachHasServiceDto> create(@RequestBody CreateBeachHasServiceDto beach) {
        BeachHasServiceDto newEntity = service.save(beach);
        return ResponseEntity.created(URI.create("/api/playas/" + newEntity.getId())).body(newEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeachHasServiceDto> update(@RequestBody CreateBeachHasServiceDto entity, @PathVariable Long id) {
        BeachHasServiceDto updatedEntity = service.update(id,entity);
        return ResponseEntity.created(URI.create("/api/playas/" + id)).body(updatedEntity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
