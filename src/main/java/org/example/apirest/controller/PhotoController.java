package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.example.apirest.service.photo.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PhotoController {

    private final PhotoService service;

    @GetMapping
    public ResponseEntity<List<PhotoDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<PhotoDto> create(@RequestBody CreatePhotoDto photo) {
        PhotoDto newPhoto = service.save(photo);
        return ResponseEntity.created(URI.create("/api/photos/" + newPhoto.getId())).body(newPhoto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhotoDto> update(@RequestBody CreatePhotoDto photo, @PathVariable Long id) {
        PhotoDto updatedPhoto = service.update(id, photo);
        return ResponseEntity.created(URI.create("/api/photos/" + id)).body(updatedPhoto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
