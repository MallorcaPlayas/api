package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.model.Photo;
import org.example.apirest.service.photo.PhotoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoServiceImpl service;

    @GetMapping
//    @PreAuthorize("hasAuthority('readBeach')")
    public ResponseEntity<List<PhotoDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('readBeach')")
    public ResponseEntity<PhotoDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping(consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
//    @PreAuthorize("hasAuthority('createBeach')")
    public ResponseEntity<PhotoDto> create(@ModelAttribute CreatePhotoDto entity) {
        PhotoDto newEntity = service.save(entity);
        return ResponseEntity.ok(newEntity);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('deleteBeach')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
