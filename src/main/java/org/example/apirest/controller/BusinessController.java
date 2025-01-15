package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.business.BusinessDto;
import org.example.apirest.dto.business.CreateBusinessDto;
import org.example.apirest.service.business.BusinessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/businesses")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BusinessController {

    private final BusinessService service;

    @GetMapping
    public ResponseEntity<List<BusinessDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<BusinessDto> create(@RequestBody CreateBusinessDto business) {
        BusinessDto newBusiness = service.save(business);
        return ResponseEntity.created(URI.create("/api/businesses/" + newBusiness.getId())).body(newBusiness);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessDto> update(@RequestBody CreateBusinessDto business, @PathVariable Long id) {
        BusinessDto updatedBusiness = service.update(id, business);
        return ResponseEntity.created(URI.create("/api/businesses/" + id)).body(updatedBusiness);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
