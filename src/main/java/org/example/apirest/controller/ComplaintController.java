package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.complaint.ComplaintDto;
import org.example.apirest.dto.complaint.CreateComplaintDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ComplaintController {

    private final ComplaintService service;

    @GetMapping
    public ResponseEntity<List<ComplaintDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplaintDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<ComplaintDto> create(@RequestBody CreateComplaintDto complaint) {
        ComplaintDto newComplaint = service.save(complaint);
        return ResponseEntity.created(URI.create("/api/complaints/" + newComplaint.getId())).body(newComplaint);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComplaintDto> update(@RequestBody CreateComplaintDto complaint, @PathVariable Long id) {
        ComplaintDto updatedComplaint = service.update(id, complaint);
        return ResponseEntity.created(URI.create("/api/complaints/" + id)).body(updatedComplaint);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
