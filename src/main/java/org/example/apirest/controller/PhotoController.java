package org.example.apirest.controller;

import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.example.apirest.service.photo.PhotoServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/photos")
@CrossOrigin(origins = "*")
public class PhotoController extends GeneralizedControllerImpl<PhotoDto, CreatePhotoDto> {
    public PhotoController(PhotoServiceImpl service) {
        super(service);
    }
}
