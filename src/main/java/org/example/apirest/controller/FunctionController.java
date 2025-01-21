package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.function.FunctionDto;
import org.example.apirest.dto.function.CreateFunctionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/functions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FunctionController {

    private final FunctionService service;

    @GetMapping
    public ResponseEntity<List<FunctionDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FunctionDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<FunctionDto> create(@RequestBody CreateFunctionDto function) {
        FunctionDto newFunction = service.save(function);
        return ResponseEntity.created(URI.create("/api/functions/" + newFunction.getId())).body(newFunction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FunctionDto> update(@RequestBody CreateFunctionDto function, @PathVariable Long id) {
        FunctionDto updatedFunction = service.update(id, function);
        return ResponseEntity.created(URI.create("/api/functions/" + id)).body(updatedFunction);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
