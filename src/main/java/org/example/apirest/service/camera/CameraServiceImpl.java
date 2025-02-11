package org.example.apirest.service.camera;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.camera.CameraDto;
import org.example.apirest.dto.camera.CreateCameraDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Camera;
import org.example.apirest.repository.CameraRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CameraServiceImpl implements DtoConverter<Camera, CameraDto, CreateCameraDto> {

    private final CameraRepository repository;
    private final ModelMapper mapper;

    public List<CameraDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public CameraDto findOne(Long id) {
        Camera camera = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Camera.class, id));
        return this.toDto(camera);
    }

    public CameraDto save(CreateCameraDto createCameraDto) {
        Camera camera = fromDto(createCameraDto);
        Camera savedCamera = repository.save(camera);
        return toDto(savedCamera);
    }

    public CameraDto update(Long id, CreateCameraDto createCameraDto) {
        Camera oldCamera = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Camera.class, id));
        Camera cameraToUpdate = fromDto(createCameraDto);

        UtilsClass.updateFields(oldCamera, cameraToUpdate);

        Camera savedCamera = repository.save(oldCamera);
        return toDto(savedCamera);
    }

    public void delete(Long id) {
        Camera camera = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Camera.class, id));
        repository.delete(camera);
    }

    @Override
    public CameraDto toDto(Camera camera) {
        return mapper.map(camera, CameraDto.class);
    }

    @Override
    public List<CameraDto> toDtoList(List<Camera> cameras) {
        return cameras.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public Camera fromDto(CreateCameraDto createCameraDto) {
        return mapper.map(createCameraDto, Camera.class);
    }

    @Override
    public List<Camera> fromDtoList(List<CreateCameraDto> createCameraDtos) {
        return createCameraDtos.stream()
                .map(this::fromDto)
                .toList();
    }
}