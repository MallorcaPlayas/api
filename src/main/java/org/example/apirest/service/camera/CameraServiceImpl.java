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

    private final CameraRepository repository;
    private final DtoConverterImpl<Camera, CameraDto, CreateCameraDto> dtoConverter;

    @Override
    public List<CameraDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), CameraDto.class);
    }

    @Override
    public CameraDto findOne(Long id) {
        Camera camera = repository.findById(id).orElseThrow(() -> new NotFoundException(Camera.class, id));
        return dtoConverter.convertDto(camera, CameraDto.class);
    }

    @Override
    public CameraDto save(CreateCameraDto camera) {
        Camera cameraToInsert = dtoConverter.convertToEntityFromCreateDto(camera, Camera.class);
        return dtoConverter.convertDto(repository.save(cameraToInsert), CameraDto.class);
    }

    @Override
    public CameraDto update(Long id, CreateCameraDto camera) {
        Camera oldCamera = repository.findById(id).orElseThrow(() -> new NotFoundException(Camera.class, id));
        Camera cameraToInsert = dtoConverter.convertToEntityFromCreateDto(camera, Camera.class);

        if (oldCamera == null) {
            return null;
        }

        UtilsClass.updateFields(oldCamera, cameraToInsert);

        return dtoConverter.convertDto(repository.save(oldCamera), CameraDto.class);
    }

    @Override
    public void delete(Long id) {
        Camera camera = repository.findById(id).orElseThrow(() -> new NotFoundException(Camera.class, id));
        repository.delete(camera);
    }
}
