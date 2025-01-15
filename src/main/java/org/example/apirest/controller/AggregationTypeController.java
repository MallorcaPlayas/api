package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.aggregationType.AggregationTypeDto;
import org.example.apirest.dto.aggregationType.CreateAggregationTypeDto;
import org.example.apirest.service.aggregationType.AggregationTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/aggregation-types")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AggregationTypeController {

    private final AggregationTypeService service;

    @GetMapping
    public ResponseEntity<List<AggregationTypeDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AggregationTypeDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<AggregationTypeDto> create(@RequestBody CreateAggregationTypeDto aggregationType) {
        AggregationTypeDto newAggregationType = service.save(aggregationType);
        return ResponseEntity.created(URI.create("/api/aggregation-types/" + newAggregationType.getId())).body(newAggregationType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AggregationTypeDto> update(@RequestBody CreateAggregationTypeDto aggregationType, @PathVariable Long id) {
        AggregationTypeDto updatedAggregationType = service.update(id, aggregationType);
        return ResponseEntity.created(URI.create("/api/aggregation-types/" + id)).body(updatedAggregationType);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
