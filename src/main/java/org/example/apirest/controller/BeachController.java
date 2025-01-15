package org.example.apirest.controller;


import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.service.beach.BeachService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/beaches")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BeachController {

    private final BeachService service;

    @GetMapping
    public ResponseEntity<List<BeachDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeachDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<BeachDto> create(@RequestBody CreateBeachDto beach) {
        BeachDto newBeach = service.save(beach);
        return ResponseEntity.created(URI.create("/api/playas/" + newBeach.getId())).body(newBeach);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeachDto> update(@RequestBody CreateBeachDto beach,@PathVariable Long id) {
        BeachDto updatedBeach = service.update(id,beach);
        return ResponseEntity.created(URI.create("/api/playas/" + id)).body(updatedBeach);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
