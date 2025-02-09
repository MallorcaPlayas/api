package org.example.apirest.controller;

import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.example.apirest.service.photo.PhotoServiceImpl;
import org.example.apirest.service.s3.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/photos")
@CrossOrigin(origins = "*")
public class PhotoController extends GeneralizedController<PhotoDto, CreatePhotoDto> {

    private final PhotoServiceImpl service;
    public PhotoController(PhotoServiceImpl service) {
        super(service);
        this.service = service;
    }

    @PostMapping("upload")
    public ResponseEntity<PhotoDto> uploadPublic(@RequestPart MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.uploadPublic(file));
    }

    @PostMapping("upload/private")
    public ResponseEntity<PhotoDto> uploadPrivate(@RequestPart MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.uploadPrivate(file));
    }

    @GetMapping("/private/{id}")
    public ResponseEntity<PhotoDto> getPrivate(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok(service.getPrivate(id));
    }

}
