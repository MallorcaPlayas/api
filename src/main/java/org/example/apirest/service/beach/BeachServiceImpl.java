package org.example.apirest.service.beach;

import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.beachHasService.BeachHasServiceDto;
import org.example.apirest.dto.beachHasService.CreateBeachHasServiceDto;
import org.example.apirest.dto.beachManager.BeachManagerDto;
import org.example.apirest.dto.beachManager.CreateBeachManagerDto;
import org.example.apirest.dto.camera.CameraDto;
import org.example.apirest.dto.camera.CreateCameraDto;
import org.example.apirest.dto.userHasRole.CreateUserHasRoleDto;
import org.example.apirest.dto.userHasRole.UserHasRoleDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.repository.*;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.service.beachManager.BeachManagerServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeachServiceImpl extends GeneralizedServiceImpl<Beach,BeachDto,CreateBeachDto,BeachRepository> {
    private final BeachManagerRepository beachManagerRepository;
    private final BeachManagerServiceImpl beachManagerService;
    private final UserRepository userRepository;
    private final BeachHasServiceRepository beachHasServiceRepository;
    private final TypeBeachRepository typeBeachRepository;
    private final CameraRepository cameraRepository;
    private final DtoConverterImpl<BeachManager,BeachManagerDto,CreateBeachManagerDto> dtoBeachManager;
    private final DtoConverterImpl<BeachHasService, BeachHasServiceDto, CreateBeachHasServiceDto> dtoBeachHasService;
    private final DtoConverterImpl<Camera, CameraDto, CreateCameraDto> dtoCamera;

    public BeachServiceImpl(BeachRepository repository, DtoConverterImpl<Beach,BeachDto,CreateBeachDto> dtoConverter, BeachManagerRepository beachManagerRepository, BeachManagerServiceImpl beachManagerService, UserRepository userRepository, BeachHasServiceRepository beachHasServiceRepository, TypeBeachRepository typeBeachRepository, CameraRepository cameraRepository, DtoConverterImpl<BeachManager, BeachManagerDto, CreateBeachManagerDto> dtoBeachManager, DtoConverterImpl<BeachHasService, BeachHasServiceDto, CreateBeachHasServiceDto> dtoBeachHasService, DtoConverterImpl<Camera, CameraDto, CreateCameraDto> dtoCamera) {
        super(repository, dtoConverter, Beach.class, BeachDto.class);
        this.beachManagerRepository = beachManagerRepository;
        this.beachManagerService = beachManagerService;
        this.userRepository = userRepository;
        this.beachHasServiceRepository = beachHasServiceRepository;
        this.typeBeachRepository = typeBeachRepository;
        this.cameraRepository = cameraRepository;
        this.dtoBeachManager = dtoBeachManager;
        this.dtoBeachHasService = dtoBeachHasService;
        this.dtoCamera = dtoCamera;
    }

    @Override
    public BeachDto save(CreateBeachDto entity) {
        Beach entityToInsert = dtoConverter.convertToEntityFromCreateDto(entity, Beach.class);

        List<BeachManager> managers = dtoBeachManager.convertToEntityListFromCreateDto(entity.getUsersInCharge(), BeachManager.class);
        for (BeachManager manager : managers) {
            manager.setBeach(entityToInsert);
        }
        entityToInsert.setUsersInCharge(managers);

        List<BeachHasService> services = dtoBeachHasService.convertToEntityListFromCreateDto(entity.getBeachHasServiceBeach(), BeachHasService.class);
        for (BeachHasService service : services) {
            service.setBeach(entityToInsert);
        }
        entityToInsert.setBeachHasServiceBeach(services);

        List<Camera> cameras = dtoCamera.convertToEntityListFromCreateDto(entity.getCameras(), Camera.class);
        for (Camera camera : cameras) {
            camera.setBeach(entityToInsert);
        }
        entityToInsert.setCameras(cameras);

        return dtoConverter.convertDto(repository.save(entityToInsert), BeachDto.class);
    }

//    @Override
//    public BeachDto update(Long id, CreateBeachDto entity) {
//        Beach old = repository.findById(id).orElseThrow(()-> new NotFoundException(Beach.class,id));
//        Beach newEntity = dtoConverter.convertToEntityFromCreateDto(entity, Beach.class);
//
//        UtilsClass.updateFields(old, newEntity);
//
//        List<BeachManager> beachManagers = entity.getUsersInCharge().stream()
//                .map(entityChild -> beachManagerRepository.findById(entityChild.getId())
//                        .orElseThrow(() -> new NotFoundException(BeachManager.class, entityChild.getId())))
//                .toList();
//
//        List<BeachHasService> beachHasServices = entity.getBeachHasServiceBeach().stream()
//                .map(entityChild -> beachHasServiceRepository.findById(entityChild.getId())
//                        .orElseThrow(() -> new NotFoundException(BeachHasService.class, entityChild.getId())))
//                .toList();
//
//        List<TypeBeach> typeBeaches = entity.getTypes().stream()
//                .map(entityChild -> typeBeachRepository.findById(entityChild.getId())
//                        .orElseThrow(() -> new NotFoundException(TypeBeach.class, entityChild.getId())))
//                .toList();
//
//        List<Camera> cameras = entity.getCameras().stream()
//                .map(entityChild -> cameraRepository.findById(entityChild.getId())
//                        .orElseThrow(() -> new NotFoundException(Camera.class, entityChild.getId())))
//                .toList();
//
//
//        old.setTypes(typeBeaches);
//        old.setCameras(cameras);
//        old.setUsersInCharge(beachManagers);
//        old.setBeachHasServiceBeach(beachHasServices);
//
//        return dtoConverter.convertDto(repository.save(old), BeachDto.class);
//    }
}
