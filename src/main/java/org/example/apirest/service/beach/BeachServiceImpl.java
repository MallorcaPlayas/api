package org.example.apirest.service.beach;

import lombok.extern.slf4j.Slf4j;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.beachHasService.BeachHasServiceDto;
import org.example.apirest.dto.beachHasService.CreateBeachHasServiceDto;
import org.example.apirest.dto.beachManager.BeachManagerDto;
import org.example.apirest.dto.beachManager.CreateBeachManagerDto;
import org.example.apirest.dto.camera.CameraDto;
import org.example.apirest.dto.camera.CreateCameraDto;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.model.beach.Beach;
import org.example.apirest.model.beach.BeachTranslation;
import org.example.apirest.repository.TranslationRepositoryMongoDB;
import org.example.apirest.repository.beach.BeachRepository;
import org.example.apirest.repository.beach.BeachTranslationRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j // Sirve para poder hacer logs y verlos en consola

@Service
public class BeachServiceImpl extends GeneralizedServiceImpl<Beach, BeachDto, CreateBeachDto, BeachRepository> {

    private final DtoConverterImpl<BeachManager, BeachManagerDto, CreateBeachManagerDto> dtoBeachManager;
    private final DtoConverterImpl<BeachHasService, BeachHasServiceDto, CreateBeachHasServiceDto> dtoBeachHasService;
    private final DtoConverterImpl<Camera, CameraDto, CreateCameraDto> dtoCamera;
    private final DtoConverterImpl<TypeBeach, TypeBeachDto, CreateTypeBeachDto> dtoTypeBeach;

    private final BeachRepository beachRepository;
    private final TranslationRepositoryMongoDB translationRepositoryMongoDB;

    private final BeachTranslationRepository beachTranslationRepository;

    public BeachServiceImpl(BeachRepository repository,
                            DtoConverterImpl<Beach, BeachDto, CreateBeachDto> dtoConverter,
                            DtoConverterImpl<BeachManager, BeachManagerDto, CreateBeachManagerDto> dtoBeachManager,
                            DtoConverterImpl<BeachHasService, BeachHasServiceDto, CreateBeachHasServiceDto> dtoBeachHasService,
                            DtoConverterImpl<Camera, CameraDto, CreateCameraDto> dtoCamera,
                            DtoConverterImpl<TypeBeach, TypeBeachDto, CreateTypeBeachDto> dtoTypeBeach, BeachTranslationRepository beachTranslationRepository, TranslationRepositoryMongoDB translationRepositoryMongoDB, BeachTranslationRepository beachTranslationRepository1) {

        super(repository, dtoConverter, Beach.class, BeachDto.class);
        this.dtoBeachManager = dtoBeachManager;
        this.dtoBeachHasService = dtoBeachHasService;
        this.dtoCamera = dtoCamera;
        this.dtoTypeBeach = dtoTypeBeach;
        this.beachRepository = repository;
        this.translationRepositoryMongoDB = translationRepositoryMongoDB;
        this.beachTranslationRepository = beachTranslationRepository1;
    }


    public Optional<Beach> findBeachWithTranslation(Long id, String language) {

        // Buscar la playa en MySQL
        Optional<Beach> beach = beachRepository.findById(id);

        System.out.println("Que playa Beach: " + beach);

        if (beach.isPresent()) {
            // Buscar la traducción en MongoDB
            TranslationMongoDB translation = translationRepositoryMongoDB.findById("Prueba2").orElse(null);

            System.out.println("Que playa translation: " + translation);

            if (translation != null) {
                // Buscar el idioma solicitado
                LanguageMongoDb languageTranslation = translation.getLanguages()
                        .stream()
                        .filter(lang -> lang.getId().equalsIgnoreCase(language))
                        .findFirst()
                        .orElse(null);

                System.out.println("Que playa languageTranslation: " + languageTranslation);
                if (languageTranslation != null) {
                    // Reemplazar la descripción de la playa con la traducción
                    beach.get().setDescription(languageTranslation.getTranslate());
                }
            }
        }
        System.out.println("Que devuelvo Beach metodo findBeach " + beach);
        return beach;
    }

    @Override
    public BeachDto findOne(Long id) {
        BeachDto beach = super.findOne(id);

        // Buscar traducciones en MongoDB
        BeachTranslation translation = beachTranslationRepository.findByBeachId(String.valueOf(id));
        if (translation != null) {
            beach.setTranslations(translation.getTranslations());
        }

        return beach;
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