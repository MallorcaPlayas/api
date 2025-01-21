package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.camera.CameraDto;
import org.example.apirest.dto.camera.CreateCameraDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.camera.CameraServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cameras")
@CrossOrigin(origins = "*")
public class CameraController extends GeneralizedController<CameraDto, CreateCameraDto> {
    public CameraController(CameraServiceImpl service) {
        super(service);
    }
}
