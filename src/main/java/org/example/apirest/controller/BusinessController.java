package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.business.BusinessDto;
import org.example.apirest.dto.business.CreateBusinessDto;
import org.example.apirest.service.business.BusinessServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/businesses")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BusinessController{
    private final BusinessServiceImpl service;

    @GetMapping
    public ResponseEntity<List<BusinessDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<BusinessDto> create(@RequestBody CreateBusinessDto entity) {
        BusinessDto newEntity = service.save(entity);
        return ResponseEntity.ok(newEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessDto> update(@RequestBody CreateBusinessDto entity, @PathVariable Long id) {
        BusinessDto updated = service.update(id, entity);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
