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
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.dto.userHasRole.CreateUserHasRoleDto;
import org.example.apirest.dto.userHasRole.UserHasRoleDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.repository.*;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.service.TranslationServiceMongoDB;
import org.example.apirest.service.beachManager.BeachManagerServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeachServiceImpl extends GeneralizedServiceImpl<Beach, BeachDto, CreateBeachDto, BeachRepository> {
    private final DtoConverterImpl<BeachManager, BeachManagerDto, CreateBeachManagerDto> dtoBeachManager;
    private final DtoConverterImpl<BeachHasService, BeachHasServiceDto, CreateBeachHasServiceDto> dtoBeachHasService;
    private final DtoConverterImpl<Camera, CameraDto, CreateCameraDto> dtoCamera;
    private final DtoConverterImpl<TypeBeach, TypeBeachDto, CreateTypeBeachDto> dtoTypeBeach;

    private final TranslationServiceMongoDB translationServiceMongoDB;

    public BeachServiceImpl(BeachRepository repository,
                            DtoConverterImpl<Beach, BeachDto, CreateBeachDto> dtoConverter,
                            DtoConverterImpl<BeachManager, BeachManagerDto, CreateBeachManagerDto> dtoBeachManager,
                            DtoConverterImpl<BeachHasService, BeachHasServiceDto, CreateBeachHasServiceDto> dtoBeachHasService,
                            DtoConverterImpl<Camera, CameraDto, CreateCameraDto> dtoCamera,
                            DtoConverterImpl<TypeBeach, TypeBeachDto, CreateTypeBeachDto> dtoTypeBeach, TranslationServiceMongoDB translationServiceMongoDB) {

        super(repository, dtoConverter, Beach.class, BeachDto.class);
        this.dtoBeachManager = dtoBeachManager;
        this.dtoBeachHasService = dtoBeachHasService;
        this.dtoCamera = dtoCamera;
        this.dtoTypeBeach = dtoTypeBeach;
        this.translationServiceMongoDB = translationServiceMongoDB;
    }

    @Override
    public BeachDto findOne(Long id) {
        Beach beach = repository.findById(id).orElseThrow(() -> new NotFoundException(Beach.class, id));
        BeachDto beachDto = dtoConverter.convertDto(beach, BeachDto.class);

        // Obtener todas las traducciones de MongoDB
        List<TranslationMongoDB> translations = translationServiceMongoDB.findAll();

        System.out.println("Estoy cogiendo todas las traducciones de MongoDB:");
        for (TranslationMongoDB translation : translations) {
            System.out.println("TranslationMongoDB: " + translation);
        }

        // Agrega logs para depuración
        System.out.println("Buscando la playa con id: " + id);
        System.out.println("TranslationKey de la playa: " + beach.getTranslationKey());

        // Filtrar traducción específica basada en la TranslationKey de la playa
        if (beach.getTranslationKey() != null) {
            TranslationMongoDB translation = translations.stream()
                    .filter(t -> t.getKey().equals(beach.getTranslationKey())) // Filtrar por clave
                    .findFirst()
                    .orElse(null);

            if (translation != null) {
                System.out.println("Traducción específica encontrada en MongoDB: " + translation);

                // Filtrar y buscar la traducción basada en el idioma (ejemplo: "fr")
                LanguageMongoDb translationLanguage = translation.getLanguages()
                        .stream()
                        .filter(lang -> lang.getId() != null && lang.getId().equals("fr")) // Cambiar idioma según necesidad
                        .findFirst()
                        .orElse(null);

                if (translationLanguage != null) {
                    System.out.println("Traducción en idioma 'fr': " + translationLanguage.getTranslate());
                    beachDto.setDescription(translationLanguage.getTranslate());
                } else {
                    System.out.println("No se encontró traducción para el idioma 'fr'");
                }
            } else {
                System.out.println("No se encontró una traducción en MongoDB para la clave: " + beach.getTranslationKey());
            }
        } else {
            System.out.println("La playa no tiene TranslationKey");
        }

        return beachDto;
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