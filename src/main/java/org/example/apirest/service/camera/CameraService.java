package org.example.apirest.service.camera;

import org.example.apirest.dto.camera.CameraDto;
import org.example.apirest.dto.camera.CreateCameraDto;

import java.util.List;

public interface CameraService {
    List<CameraDto> findAll();            // Obtener todas las cámaras
    CameraDto findOne(Long id);           // Obtener una cámara por su id
    CameraDto save(CreateCameraDto camera);   // Guardar una nueva cámara
    CameraDto update(Long id, CreateCameraDto camera);   // Actualizar una cámara existente
    void delete(Long id);                 // Eliminar una cámara por su id
}
