package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.excursion.ExcursionDto;
import org.example.apirest.dto.excursion.CreateExcursionDto;
import org.example.apirest.service.excursion.ExcursionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/excursions")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ExcursionController {

    private final ExcursionServiceImpl service;

    @GetMapping
    public ResponseEntity<List<ExcursionDto>> index() {
        List<ExcursionDto> excursions = service.findAll();
        return ResponseEntity.ok(excursions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExcursionDto> show(@PathVariable Long id) {
        ExcursionDto excursion = service.findOne(id);
        return ResponseEntity.ok(excursion);
    }

    @PostMapping
    public ResponseEntity<ExcursionDto> create(@RequestBody CreateExcursionDto entity) {
        ExcursionDto newExcursion = service.save(entity);
        return ResponseEntity.ok(newExcursion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExcursionDto> update(@PathVariable Long id, @RequestBody CreateExcursionDto entity) {
        ExcursionDto updatedExcursion = service.update(id, entity);
        return ResponseEntity.ok(updatedExcursion);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

