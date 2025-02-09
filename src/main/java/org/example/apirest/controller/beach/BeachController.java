package org.example.apirest.controller.beach;


import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beaches")
@CrossOrigin(origins = "*")
public class BeachController {
    private final BeachServiceImpl service;

    public BeachController(BeachServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('readBeach')")
    public ResponseEntity<List<BeachDto>> index() {
        return ResponseEntity.ok(service.findAll());
    // para usarlo en postman: 127.0.0.1:8080/beaches/translated?language=de
    @GetMapping("/translated")
    public ResponseEntity<List<BeachDto>> index( @RequestParam(defaultValue = "de") String language) {
        return ResponseEntity.ok(service.findAllTranslate(language));
    }

    // para usarlo en postman: 127.0.0.1:8080/beaches/2/translated?language=de
    @GetMapping("/{id}/translated")
    @PreAuthorize("hasAuthority('readBeach')")
    public ResponseEntity<BeachDto> show(@PathVariable Long id, @RequestParam String requestedLanguage) {
        return ResponseEntity.ok(service.findOneTranslate(id, requestedLanguage));
    public ResponseEntity<BeachDto> show(@PathVariable Long id, @RequestParam String language) {
        return ResponseEntity.ok(service.findOneTranslate(id, language));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('createBeach')")
    public ResponseEntity<BeachDto> create(@RequestBody CreateBeachDto entity) {
        BeachDto newEntity = service.save(entity);
        return ResponseEntity.ok(newEntity);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('updateBeach')")
    public ResponseEntity<BeachDto> update(@RequestBody CreateBeachDto entity, @PathVariable Long id) {
        BeachDto updated = service.update(id, entity);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('deleteBeach')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
