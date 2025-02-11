package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.function.FunctionDto;
import org.example.apirest.dto.function.CreateFunctionDto;
import org.example.apirest.service.function.FunctionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/functions")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class FunctionController {

    private final FunctionServiceImpl service;

    @GetMapping
    public ResponseEntity<List<FunctionDto>> index() {
        List<FunctionDto> functions = service.findAll();
        return ResponseEntity.ok(functions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FunctionDto> show(@PathVariable Long id) {
        FunctionDto function = service.findOne(id);
        return ResponseEntity.ok(function);
    }

    @PostMapping
    public ResponseEntity<FunctionDto> create(@RequestBody CreateFunctionDto entity) {
        FunctionDto newFunction = service.save(entity);
        return ResponseEntity.ok(newFunction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FunctionDto> update(@PathVariable Long id, @RequestBody CreateFunctionDto entity) {
        FunctionDto updatedFunction = service.update(id, entity);
        return ResponseEntity.ok(updatedFunction);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
