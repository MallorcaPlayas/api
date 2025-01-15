package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.excursion.ExcursionDto;
import org.example.apirest.dto.excursion.CreateExcursionDto;
import org.example.apirest.service.excursion.ExcursionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/excursions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ExcursionController {

    private final ExcursionService service;

    @GetMapping
    public ResponseEntity<List<ExcursionDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExcursionDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<ExcursionDto> create(@RequestBody CreateExcursionDto excursion) {
        ExcursionDto newExcursion = service.save(excursion);
        return ResponseEntity.created(URI.create("/api/excursions/" + newExcursion.getId())).body(newExcursion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExcursionDto> update(@RequestBody CreateExcursionDto excursion, @PathVariable Long id) {
        ExcursionDto updatedExcursion = service.update(id, excursion);
        return ResponseEntity.created(URI.create("/api/excursions/" + id)).body(updatedExcursion);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
