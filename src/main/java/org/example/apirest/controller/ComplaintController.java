package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.complaint.ComplaintDto;
import org.example.apirest.dto.complaint.CreateComplaintDto;
import org.example.apirest.service.complaint.ComplaintServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintServiceImpl service;

    @GetMapping
    @PreAuthorize("hasAuthority('readComplaint')")
    public ResponseEntity<List<ComplaintDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('readComplaint')")
    public ResponseEntity<ComplaintDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('createComplaint')")
    public ResponseEntity<ComplaintDto> create(@RequestBody CreateComplaintDto entity) {
        ComplaintDto newEntity = service.save(entity);
        return ResponseEntity.ok(newEntity);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('updateComplaint')")
    public ResponseEntity<ComplaintDto> update(@RequestBody CreateComplaintDto entity, @PathVariable Long id) {
        ComplaintDto updated = service.update(id, entity);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('deleteComplaint')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
