package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.controller.validators.Validator;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.service.photo.PhotoServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/photos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoServiceImpl service;
    private final Validator<CreatePhotoDto> photoValidator;

    @GetMapping
    public ResponseEntity<List<PhotoDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping(consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PhotoDto> create(@ModelAttribute CreatePhotoDto entity) {
        photoValidator.validate(entity);
        PhotoDto newEntity = service.save(entity);
        return ResponseEntity.ok(newEntity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
