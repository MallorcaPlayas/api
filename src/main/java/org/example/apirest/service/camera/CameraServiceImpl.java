package org.example.apirest.service.camera;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.camera.CameraDto;
import org.example.apirest.dto.camera.CreateCameraDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Camera;
import org.example.apirest.repository.CameraRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CameraServiceImpl implements CameraService {

    private final CameraRepository repository;  // Repositorio para interactuar con la base de datos
    private final DtoConverterImpl<Camera, CameraDto, CreateCameraDto> dtoConverter;  // Conversor de DTOs

    @Override
    public List<CameraDto> findAll() {
        // Obtiene todas las cámaras y las convierte a DTOs
        return dtoConverter.convertDtoList(repository.findAll(), CameraDto.class);
    }

    @Override
    public CameraDto findOne(Long id) {
        // Busca una cámara por su id y la convierte a DTO
        Camera camera = repository.findById(id).orElseThrow(() -> new NotFoundException(Camera.class, id));
        return dtoConverter.convertDto(camera, CameraDto.class);
    }

    @Override
    public CameraDto save(CreateCameraDto camera) {
        // Convierte el DTO de creación a entidad y guarda la cámara
        Camera cameraToInsert = dtoConverter.convertToEntityFromCreateDto(camera, Camera.class);
        return dtoConverter.convertDto(repository.save(cameraToInsert), CameraDto.class);
    }

    @Override
    public CameraDto update(Long id, CreateCameraDto camera) {
        // Actualiza una cámara existente
        Camera oldCamera = repository.findById(id).orElseThrow(() -> new NotFoundException(Camera.class, id));
        Camera cameraToInsert = dtoConverter.convertToEntityFromCreateDto(camera, Camera.class);

        // Si la cámara no existe, se lanza la excepción
        if (oldCamera == null) {
            return null;
        }

        // Utiliza un método de utilidad para actualizar los campos de la cámara
        UtilsClass.updateFields(oldCamera, cameraToInsert);

        // Guarda y devuelve la cámara actualizada
        return dtoConverter.convertDto(repository.save(oldCamera), CameraDto.class);
    }

    @Override
    public void delete(Long id) {
        // Elimina una cámara por su id
        Camera camera = repository.findById(id).orElseThrow(() -> new NotFoundException(Camera.class, id));
        repository.delete(camera);
    }
}
