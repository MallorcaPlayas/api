package org.example.apirest.service.camera;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.businessType.BusinessTypeDto;
import org.example.apirest.dto.businessType.CreateBusinessTypeDto;
import org.example.apirest.dto.camera.CameraDto;
import org.example.apirest.dto.camera.CreateCameraDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.BusinessType;
import org.example.apirest.model.Camera;
import org.example.apirest.repository.BusinessTypeRepository;
import org.example.apirest.repository.CameraRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraServiceImpl extends GeneralizedServiceImpl<Camera, CameraDto, CreateCameraDto, CameraRepository> {
    public CameraServiceImpl(CameraRepository repository, DtoConverterImpl<Camera,CameraDto,CreateCameraDto> dtoConverter) {
        super(repository, dtoConverter, Camera.class, CameraDto.class);
    }
}
