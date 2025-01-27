package org.example.apirest.controller;

import org.example.apirest.dto.camera.CameraDto;
import org.example.apirest.dto.camera.CreateCameraDto;
import org.example.apirest.service.camera.CameraServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cameras")
@CrossOrigin(origins = "*")
public class CameraController extends GeneralizedController<CameraDto, CreateCameraDto> {
    public CameraController(CameraServiceImpl service) {
        super(service);
    }
}
