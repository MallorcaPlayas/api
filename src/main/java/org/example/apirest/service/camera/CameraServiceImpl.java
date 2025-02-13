package org.example.apirest.service.camera;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.camera.CameraDto;
import org.example.apirest.dto.camera.CreateCameraDto;
import org.example.apirest.model.Camera;
import org.example.apirest.repository.CameraRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CameraServiceImpl extends GeneralizedServiceImpl<Camera, CameraDto, CreateCameraDto, CameraRepository> {
    public CameraServiceImpl(CameraRepository repository, DtoConverterGeneralizedImpl<Camera,CameraDto,CreateCameraDto> dtoConverter) {
        super(repository, dtoConverter, Camera.class, CameraDto.class);
    }
}
