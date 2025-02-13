package org.example.apirest.service.beach;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.beachHasService.BeachHasServiceDto;
import org.example.apirest.dto.beachHasService.CreateBeachHasServiceDto;
import org.example.apirest.dto.beachManager.BeachManagerDto;
import org.example.apirest.dto.beachManager.CreateBeachManagerDto;
import org.example.apirest.dto.camera.CameraDto;
import org.example.apirest.dto.camera.CreateCameraDto;
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.repository.*;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeachServiceImpl extends GeneralizedServiceImpl<Beach, BeachDto, CreateBeachDto, BeachRepository> {
    private final DtoConverterGeneralizedImpl<BeachManager, BeachManagerDto, CreateBeachManagerDto> dtoBeachManager;
    private final DtoConverterGeneralizedImpl<BeachHasService, BeachHasServiceDto, CreateBeachHasServiceDto> dtoBeachHasService;
    private final DtoConverterGeneralizedImpl<Camera, CameraDto, CreateCameraDto> dtoCamera;
    private final DtoConverterGeneralizedImpl<TypeBeach, TypeBeachDto, CreateTypeBeachDto> dtoTypeBeach;
    private final DtoConverterGeneralizedImpl<Location, LocationDto, CreateLocationDto> dtoLocation;
//    private final DtoConverterPhoto dtoConverterPhoto;
//    private final S3Service s3Service;

    public BeachServiceImpl(BeachRepository repository,
                            DtoConverterGeneralizedImpl<Beach, BeachDto, CreateBeachDto> dtoConverter,
                            DtoConverterGeneralizedImpl<BeachManager, BeachManagerDto, CreateBeachManagerDto> dtoBeachManager,
                            DtoConverterGeneralizedImpl<BeachHasService, BeachHasServiceDto, CreateBeachHasServiceDto> dtoBeachHasService,
                            DtoConverterGeneralizedImpl<Camera, CameraDto, CreateCameraDto> dtoCamera,
                            DtoConverterGeneralizedImpl<TypeBeach, TypeBeachDto, CreateTypeBeachDto> dtoTypeBeach,
                            DtoConverterGeneralizedImpl<Location, LocationDto, CreateLocationDto> dtoLocation) {

        super(repository, dtoConverter, Beach.class, BeachDto.class);
        this.dtoBeachManager = dtoBeachManager;
        this.dtoBeachHasService = dtoBeachHasService;
        this.dtoCamera = dtoCamera;
        this.dtoTypeBeach = dtoTypeBeach;
        this.dtoLocation = dtoLocation;
//        this.dtoConverterPhoto = dtoConverterPhoto;
//        this.s3Service = s3Service;
    }

    @Override
    public BeachDto findOne(Long id) {
        Beach beach = repository.findById(id).orElseThrow(() -> new NotFoundException(entityClass, id));
        return dtoConverter.convertDto(beach, BeachDto.class);
    }

    @Override
    public List<BeachDto> findAll() {
        List<Beach> beaches = repository.findAll();
        return dtoConverter.convertDtoList(beaches,BeachDto.class);
    }


    @Override
    public BeachDto save(CreateBeachDto entity) {
        Beach entityToInsert = dtoConverter.convertToEntityFromCreateDto(entity, Beach.class);

        if (entity.getTypes() != null) {
            List<TypeBeach> typeBeaches = dtoTypeBeach.convertToEntityListFromDto(entity.getTypes(), TypeBeach.class);
            entityToInsert.setTypes(typeBeaches);
        }

        if (entity.getUsersInCharge() != null) {
            List<BeachManager> managers = dtoBeachManager.convertToEntityListFromCreateDto(entity.getUsersInCharge(), BeachManager.class);
            for (BeachManager manager : managers) {
                manager.setBeach(entityToInsert);
            }
            entityToInsert.setUsersInCharge(managers);
        }

        if (entity.getBeachHasServiceBeach() != null) {
            List<BeachHasService> services = dtoBeachHasService.convertToEntityListFromCreateDto(entity.getBeachHasServiceBeach(), BeachHasService.class);
            for (BeachHasService service : services) {
                service.setBeach(entityToInsert);
            }
            entityToInsert.setBeachHasServiceBeach(services);
        }

        if (entity.getCameras() != null) {
            List<Camera> cameras = dtoCamera.convertToEntityListFromCreateDto(entity.getCameras(), Camera.class);
            for (Camera camera : cameras) {
                camera.setBeach(entityToInsert);
            }
            entityToInsert.setCameras(cameras);
        }

        if (entity.getLocation() != null) {
            Location location = dtoLocation.convertToEntityFromCreateDto(entity.getLocation(), Location.class);
            entityToInsert.setLocation(location);
        }

        return dtoConverter.convertDto(repository.save(entityToInsert), BeachDto.class);

    }

    @Override
    public BeachDto update(Long id, CreateBeachDto entity) {
        Beach old = repository.findById(id).orElseThrow(() -> new NotFoundException(Beach.class, id));
        Beach newEntity = dtoConverter.convertToEntityFromCreateDto(entity, Beach.class);

        Utils.updateFields(old, newEntity);

        if (entity.getTypes() != null) {
            old.getTypes().clear();
            List<TypeBeach> typeBeaches = dtoTypeBeach.convertToEntityListFromDto(entity.getTypes(), TypeBeach.class);
            old.getTypes().addAll(typeBeaches);
        }

        if (entity.getBeachHasServiceBeach() != null) {
            old.getBeachHasServiceBeach().clear();
            List<BeachHasService> beachHasServices = dtoBeachHasService.convertToEntityListFromCreateDto(entity.getBeachHasServiceBeach(), BeachHasService.class);
            for (BeachHasService beachHasService : beachHasServices) {
                beachHasService.setBeach(old);
            }
            old.getBeachHasServiceBeach().addAll(beachHasServices);
        }

        if (entity.getUsersInCharge() != null) {
            old.getUsersInCharge().clear();
            List<BeachManager> beachManagers = dtoBeachManager.convertToEntityListFromCreateDto(entity.getUsersInCharge(), BeachManager.class);
            for (BeachManager usersInCharge : beachManagers) {
                usersInCharge.setBeach(old);
            }
            old.getUsersInCharge().addAll(beachManagers);
        }

        if (entity.getCameras() != null) {
            old.getCameras().clear();
            List<Camera> cameras = dtoCamera.convertToEntityListFromCreateDto(entity.getCameras(), Camera.class);
            for (Camera camera : cameras) {
                camera.setBeach(old);
            }
            old.getCameras().addAll(cameras);
        }

        return dtoConverter.convertDto(repository.save(old), BeachDto.class);
    }
}