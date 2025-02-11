package org.example.apirest.service.beach;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.repository.*;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.service.photo.PhotoServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeachServiceImpl implements DtoConverter<Beach,BeachDto,CreateBeachDto> {

    private final ModelMapper modelMapper;
    private final BeachRepository repository;
    private final PhotoServiceImpl photoService;

    public List<BeachDto> findAll() {
        List<Beach> beaches = repository.findAll();
        return this.toDtoList(beaches);
    }

    public BeachDto findOne(Long id) {
        Beach beach = repository.findById(id).orElseThrow(() -> new NotFoundException(Beach.class, id));
        return this.toDto(beach);
    }

    public void delete(Long id) {
        Beach beach = repository.findById(id).orElseThrow(() -> new NotFoundException(Beach.class, id));
        repository.delete(beach);
    }


    public BeachDto save(CreateBeachDto createBeachDto) {
        Beach beach = this.fromDto(createBeachDto);

//        if (entity.getTypes() != null) {
//            List<TypeBeach> typeBeaches = dtoTypeBeach.convertToEntityListFromDto(entity.getTypes(), TypeBeach.class);
//            entityToInsert.setTypes(typeBeaches);
//        }
//
//        if (entity.getUsersInCharge() != null) {
//            List<BeachManager> managers = dtoBeachManager.convertToEntityListFromCreateDto(entity.getUsersInCharge(), BeachManager.class);
//            for (BeachManager manager : managers) {
//                manager.setBeach(entityToInsert);
//            }
//            entityToInsert.setUsersInCharge(managers);
//        }
//
//        if (entity.getBeachHasServiceBeach() != null) {
//            List<BeachHasService> services = dtoBeachHasService.convertToEntityListFromCreateDto(entity.getBeachHasServiceBeach(), BeachHasService.class);
//            for (BeachHasService service : services) {
//                service.setBeach(entityToInsert);
//            }
//            entityToInsert.setBeachHasServiceBeach(services);
//        }
//
//        if (entity.getCameras() != null) {
//            List<Camera> cameras = dtoCamera.convertToEntityListFromCreateDto(entity.getCameras(), Camera.class);
//            for (Camera camera : cameras) {
//                camera.setBeach(entityToInsert);
//            }
//            entityToInsert.setCameras(cameras);
//        }
//
//        if (entity.getLocation() != null) {
//            Location location = dtoLocation.convertToEntityFromCreateDto(entity.getLocation(), Location.class);
//            entityToInsert.setLocation(location);
//        }
//
//        if (entity.getPhotos() != null) {
//            List<Photo> photos = dtoConverterPhoto.convertToEntityListFromCreateDto(entity.getPhotos(), Photo.class);
//        }
        return toDto(beach);
    }

    public BeachDto update(CreateBeachDto createBeachDto , Long id) {
        Beach old = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Beach.class, id));
        Beach newEntity = this.fromDto(createBeachDto);

        UtilsClass.updateFields(old, newEntity);

//        if (entity.getTypes() != null) {
//            old.getTypes().clear();
//            List<TypeBeach> typeBeaches = dtoTypeBeach.convertToEntityListFromDto(entity.getTypes(), TypeBeach.class);
//            old.getTypes().addAll(typeBeaches);
//        }
//
//        if (entity.getBeachHasServiceBeach() != null) {
//            old.getBeachHasServiceBeach().clear();
//            List<BeachHasService> beachHasServices = dtoBeachHasService.convertToEntityListFromCreateDto(entity.getBeachHasServiceBeach(), BeachHasService.class);
//            for (BeachHasService beachHasService : beachHasServices) {
//                beachHasService.setBeach(old);
//            }
//            old.getBeachHasServiceBeach().addAll(beachHasServices);
//        }
//
//        if (entity.getUsersInCharge() != null) {
//            old.getUsersInCharge().clear();
//            List<BeachManager> beachManagers = dtoBeachManager.convertToEntityListFromCreateDto(entity.getUsersInCharge(), BeachManager.class);
//            for (BeachManager usersInCharge : beachManagers) {
//                usersInCharge.setBeach(old);
//            }
//            old.getUsersInCharge().addAll(beachManagers);
//        }
//
//        if (entity.getCameras() != null) {
//            old.getCameras().clear();
//            List<Camera> cameras = dtoCamera.convertToEntityListFromCreateDto(entity.getCameras(), Camera.class);
//            for (Camera camera : cameras) {
//                camera.setBeach(old);
//            }
//            old.getCameras().addAll(cameras);
//        }
        Beach beach = repository.save(old);
        return this.toDto(beach);
    }

    @Override
    public BeachDto toDto(Beach beach){
        List<Photo> photos = beach.getPhotos();
        List<PhotoDto> photoDtos = photoService.toDtoList(photos);

        BeachDto beachDto = modelMapper.map(beach, BeachDto.class);
        beachDto.setPhotos(photoDtos);

        return beachDto;
    }

    @Override
    public List<BeachDto> toDtoList(List<Beach> entities){
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public Beach fromDto(CreateBeachDto createBeachDto){
        List<CreatePhotoDto> createPhotoDtos =  createBeachDto.getPhotos();
        Beach beach = modelMapper.map(createBeachDto, Beach.class);
        beach.setPhotos(photoService.fromDtoList(createPhotoDtos));
        return beach;
    }

    @Override
    public List<Beach> fromDtoList(List<CreateBeachDto> createBeachDtos){
        return createBeachDtos.stream().map(this::fromDto).toList();
    }
}