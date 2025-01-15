package org.example.apirest.service.camera;

import org.example.apirest.dto.camera.CameraDto;
import org.example.apirest.dto.camera.CreateCameraDto;

import java.util.List;

public interface CameraService {
    List<CameraDto> findAll();
    CameraDto findOne(Long id);
    CameraDto save(CreateCameraDto camera);
    CameraDto update(Long id, CreateCameraDto camera);
    void delete(Long id);
}
