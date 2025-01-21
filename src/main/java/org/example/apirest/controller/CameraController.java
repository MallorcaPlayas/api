package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.camera.CameraDto;
import org.example.apirest.dto.camera.CreateCameraDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cameras")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CameraController {

    private final CameraService service;

    @GetMapping
    public ResponseEntity<List<CameraDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CameraDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<CameraDto> create(@RequestBody CreateCameraDto camera) {
        CameraDto newCamera = service.save(camera);
        return ResponseEntity.created(URI.create("/api/cameras/" + newCamera.getId())).body(newCamera);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CameraDto> update(@RequestBody CreateCameraDto camera, @PathVariable Long id) {
        CameraDto updatedCamera = service.update(id, camera);
        return ResponseEntity.created(URI.create("/api/cameras/" + id)).body(updatedCamera);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
