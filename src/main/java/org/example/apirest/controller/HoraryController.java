package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.horary.HoraryDto;
import org.example.apirest.dto.horary.CreateHoraryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/horaries")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HoraryController {

    private final HoraryService service;

    @GetMapping
    public ResponseEntity<List<HoraryDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HoraryDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HoraryDto> create(@RequestBody CreateHoraryDto horary) {
        HoraryDto newHorary = service.save(horary);
        return ResponseEntity.created(URI.create("/api/horaries/" + newHorary.getId())).body(newHorary);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HoraryDto> update(@RequestBody CreateHoraryDto horary, @PathVariable Long id) {
        HoraryDto updatedHorary = service.update(id, horary);
        return ResponseEntity.created(URI.create("/api/horaries/" + id)).body(updatedHorary);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
