package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.photo.PhotoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/photos")
@CrossOrigin(origins = "*")
public class PhotoController extends GeneralizedController<PhotoDto, CreatePhotoDto> {
    public PhotoController(PhotoServiceImpl service) {
        super(service);
    }
}
