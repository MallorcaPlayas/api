package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.businessType.BusinessTypeDto;
import org.example.apirest.dto.businessType.CreateBusinessTypeDto;
import org.example.apirest.service.businessType.BusinessTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/business-types")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BusinessTypeController {

    private final BusinessTypeService service;

    @GetMapping
    public ResponseEntity<List<BusinessTypeDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessTypeDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<BusinessTypeDto> create(@RequestBody CreateBusinessTypeDto businessType) {
        BusinessTypeDto newBusinessType = service.save(businessType);
        return ResponseEntity.created(URI.create("/api/business-types/" + newBusinessType.getId())).body(newBusinessType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessTypeDto> update(@RequestBody CreateBusinessTypeDto businessType, @PathVariable Long id) {
        BusinessTypeDto updatedBusinessType = service.update(id, businessType);
        return ResponseEntity.created(URI.create("/api/business-types/" + id)).body(updatedBusinessType);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
