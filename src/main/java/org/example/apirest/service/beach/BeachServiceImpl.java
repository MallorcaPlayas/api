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
import org.example.apirest.dto.location.CreateLocationDto;
import org.example.apirest.dto.location.LocationDto;
import org.example.apirest.dto.photo.DtoConverterPhoto;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.dto.userHasRole.CreateUserHasRoleDto;
import org.example.apirest.dto.userHasRole.UserHasRoleDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.repository.*;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.service.beachManager.BeachManagerServiceImpl;
import org.example.apirest.service.photo.PhotoServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeachServiceImpl extends GeneralizedServiceImpl<Beach, BeachDto, CreateBeachDto, BeachRepository> {
    private final DtoConverterImpl<BeachManager, BeachManagerDto, CreateBeachManagerDto> dtoBeachManager;
    private final DtoConverterImpl<BeachHasService, BeachHasServiceDto, CreateBeachHasServiceDto> dtoBeachHasService;
    private final DtoConverterImpl<Camera, CameraDto, CreateCameraDto> dtoCamera;
    private final DtoConverterImpl<TypeBeach, TypeBeachDto, CreateTypeBeachDto> dtoTypeBeach;
    private final DtoConverterImpl<Location, LocationDto, CreateLocationDto> dtoLocation;
    private final DtoConverterPhoto dtoConverterPhoto;

    public BeachServiceImpl(BeachRepository repository,
                            DtoConverterImpl<Beach, BeachDto, CreateBeachDto> dtoConverter,
                            DtoConverterImpl<BeachManager, BeachManagerDto, CreateBeachManagerDto> dtoBeachManager,
                            DtoConverterImpl<BeachHasService, BeachHasServiceDto, CreateBeachHasServiceDto> dtoBeachHasService,
                            DtoConverterImpl<Camera, CameraDto, CreateCameraDto> dtoCamera,
                            DtoConverterImpl<TypeBeach, TypeBeachDto, CreateTypeBeachDto> dtoTypeBeach,
                            DtoConverterImpl<Location, LocationDto, CreateLocationDto> dtoLocation,
                            DtoConverterPhoto dtoConverterPhoto) {

        super(repository, dtoConverter, Beach.class, BeachDto.class);
        this.dtoBeachManager = dtoBeachManager;
        this.dtoBeachHasService = dtoBeachHasService;
        this.dtoCamera = dtoCamera;
        this.dtoTypeBeach = dtoTypeBeach;
        this.dtoLocation = dtoLocation;
        this.dtoConverterPhoto = dtoConverterPhoto;
    }

    @Override
    public BeachDto findOne(Long id) {
        Beach beach = repository.findById(id).orElseThrow(()-> new NotFoundException(entityClass,id));
        List<Photo> photos = beach.getPhotos();
        List<PhotoDto> photoDtos = photos.stream().map(photo -> dtoConverterPhoto.convertDto(photo, PhotoDto.class)).toList();
        BeachDto beachDto = dtoConverter.convertDto(beach, BeachDto.class);
        beachDto.setPhotos(photoDtos);
        return beachDto;
    }

    @Override
    public List<BeachDto> findAll() {
        List<Beach> beaches = repository.findAll();
        List<BeachDto> beachDtos = new ArrayList<>();

        return beaches.stream()
                .map(beach -> {
                    List<Photo> photos = beach.getPhotos();
                    List<PhotoDto> photoDtos = photos.stream().map(photo -> dtoConverterPhoto.convertDto(photo, PhotoDto.class)).toList();
                    BeachDto beachDto = dtoConverter.convertDto(beach, BeachDto.class);
                    beachDto.setPhotos(photoDtos);
                    return beachDto;
                })
                .toList();
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

        if(entity.getLocation() != null){
            Location location = dtoLocation.convertToEntityFromCreateDto(entity.getLocation(),Location.class);
            entityToInsert.setLocation(location);
        }

        if(entity.getPhotos() != null){
            List<Photo> photos = dtoConverterPhoto.convertToEntityListFromCreateDto(entity.getPhotos(),Photo.class);
            entityToInsert.setPhotos(photos);
        }

        return dtoConverter.convertDto(repository.save(entityToInsert), BeachDto.class);

    }

    @Override
    public BeachDto update(Long id, CreateBeachDto entity) {
        Beach old = repository.findById(id).orElseThrow(() -> new NotFoundException(Beach.class, id));
        Beach newEntity = dtoConverter.convertToEntityFromCreateDto(entity, Beach.class);

        UtilsClass.updateFields(old, newEntity);

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