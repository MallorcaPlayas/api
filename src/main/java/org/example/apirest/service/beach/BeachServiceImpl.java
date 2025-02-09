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
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.model.beach.Beach;
import org.example.apirest.model.beach.BeachTranslationMongoDB;
import org.example.apirest.repository.beach.BeachRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeachServiceImpl extends GeneralizedServiceImpl<Beach, BeachDto, CreateBeachDto, BeachRepository> {
    private final DtoConverterImpl<BeachManager, BeachManagerDto, CreateBeachManagerDto> dtoBeachManager;
    private final DtoConverterImpl<BeachHasService, BeachHasServiceDto, CreateBeachHasServiceDto> dtoBeachHasService;
    private final DtoConverterImpl<Camera, CameraDto, CreateCameraDto> dtoCamera;
    private final DtoConverterImpl<TypeBeach, TypeBeachDto, CreateTypeBeachDto> dtoTypeBeach;

    private final BeachTranslationMongoService beachTranslationMongoService;

    public BeachServiceImpl(BeachRepository repository,
                            DtoConverterImpl<Beach, BeachDto, CreateBeachDto> dtoConverter,
                            DtoConverterImpl<BeachManager, BeachManagerDto, CreateBeachManagerDto> dtoBeachManager,
                            DtoConverterImpl<BeachHasService, BeachHasServiceDto, CreateBeachHasServiceDto> dtoBeachHasService,
                            DtoConverterImpl<Camera, CameraDto, CreateCameraDto> dtoCamera,
                            DtoConverterImpl<TypeBeach, TypeBeachDto, CreateTypeBeachDto> dtoTypeBeach, BeachTranslationMongoService translationServiceMongoDB) {

        super(repository, dtoConverter, Beach.class, BeachDto.class);
        this.dtoBeachManager = dtoBeachManager;
        this.dtoBeachHasService = dtoBeachHasService;
        this.dtoCamera = dtoCamera;
        this.dtoTypeBeach = dtoTypeBeach;
        this.beachTranslationMongoService = translationServiceMongoDB;
    }

    @Override
    public BeachDto findOne(Long id) {
        Beach beach = repository.findById(id).orElseThrow(() -> new NotFoundException(Beach.class, id));
        BeachDto beachDto = dtoConverter.convertDto(beach, BeachDto.class);

        // Buscar la traducción específica en MongoDB para esta playa
        if (beach.getTranslationKey() != null) {
            BeachTranslationMongoDB translation = translationServiceMongoDB.findByKey(beach.getTranslationKey());
            if (translation != null) {
                System.out.println("Traducción específica encontrada en MongoDB: " + translation);

                // Obtener la traducción de 'description' para el idioma requerido (ejemplo: 'fr')
                List<LanguageMongoDb> descriptionTranslations = translation.getTranslations().get("description");
                if (descriptionTranslations != null) {
                    LanguageMongoDb translationLanguage = descriptionTranslations.stream()
                            .filter(lang -> lang.getId() != null && lang.getId().equals("ger")) // Cambiar idioma según necesidad
                            .findFirst()
                            .orElse(null);

                    if (translationLanguage != null) {
                        System.out.println("Traducción en idioma 'fr' para 'description': " + translationLanguage.getTranslate());
                        beachDto.setDescription(translationLanguage.getTranslate());
                    } else {
                        System.out.println("No se encontró traducción para el idioma 'fr' en 'description'");
                    }
                } else {
                    System.out.println("'description' no tiene traducciones en el documento de MongoDB.");
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