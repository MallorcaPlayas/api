package org.example.apirest.service.beach;

import org.example.apirest.dto.DtoConverter;
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
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.photo.PhotoDtoConverter;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.model.beach.Beach;
import org.example.apirest.model.beach.BeachTranslationMongoDB;
import org.example.apirest.repository.beach.BeachRepository;
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
//    private final DtoConverterGeneralizedImpl<Location, LocationDto, CreateLocationDto> dtoLocation;
    private final DtoConverter<Photo, PhotoDto> photoDtoConverter;
    private final BeachTranslationMongoService beachTranslationMongoService;

    public BeachServiceImpl(BeachRepository repository,
                            DtoConverterGeneralizedImpl<Beach, BeachDto, CreateBeachDto> dtoConverter,
                            DtoConverterGeneralizedImpl<BeachManager, BeachManagerDto, CreateBeachManagerDto> dtoBeachManager,
                            DtoConverterGeneralizedImpl<BeachHasService, BeachHasServiceDto, CreateBeachHasServiceDto> dtoBeachHasService,
                            DtoConverterGeneralizedImpl<Camera, CameraDto, CreateCameraDto> dtoCamera,
                            DtoConverterGeneralizedImpl<TypeBeach, TypeBeachDto, CreateTypeBeachDto> dtoTypeBeach,
                            BeachTranslationMongoService translationServiceMongoDB,
                            DtoConverter<Photo, PhotoDto> photoDtoConverter) {

        super(repository, dtoConverter, Beach.class, BeachDto.class);
        this.dtoBeachManager = dtoBeachManager;
        this.dtoBeachHasService = dtoBeachHasService;
        this.dtoCamera = dtoCamera;
        this.dtoTypeBeach = dtoTypeBeach;
//        this.dtoLocation = dtoLocation;
        this.photoDtoConverter = photoDtoConverter;
        this.beachTranslationMongoService = translationServiceMongoDB;
    }

    @Override
    public BeachDto findOne(Long id) {

        Beach beach = repository.findById(id).orElseThrow(() -> new NotFoundException(entityClass, id));
        BeachDto beachDto = dtoConverter.convertDto(beach, BeachDto.class);

        List<PhotoDto> photoDtos = photoDtoConverter.entityListToDtoList(beach.getPhotos());
        beachDto.setPhotos(photoDtos);

        return beachDto;
    }


    public BeachDto findOneTranslate(Long id, String requestedLanguage) {
        Beach beach = repository.findById(id).orElseThrow(() -> new NotFoundException(Beach.class, id));
        BeachDto beachDto = dtoConverter.convertDto(beach, BeachDto.class);
        // Buscar la traducción específica en MongoDB para esta playa
        // Indico el id de la playa para buscar la traducción específica en MongoDB
        // todos los documentos en MongoDB tienen la clave 'beach_' + id
        BeachTranslationMongoDB beachTranslationData = beachTranslationMongoService.findByKey("beach_" + id);
        if (beachTranslationData != null) {
            // Obtener la traducción de 'description' para el idioma requerido (ejemplo: 'ger')
            List<TranslatedLanguageMongoDb> descriptionTranslations = beachTranslationData.getTranslations().get("description");
            if (descriptionTranslations != null) {
                TranslatedLanguageMongoDb translationLanguage = descriptionTranslations.stream()
                        .filter(lang -> lang.getId() != null && lang.getId().equals(requestedLanguage)) // Cambiar idioma según necesidad
                        .findFirst()
                        .orElse(null);

                if (translationLanguage != null) {
                    beachDto.setDescription(translationLanguage.getTranslate());
                }
            }
        }
        return beachDto;
    }


    public List<BeachDto> findAllTranslate(String requestedLanguage) {
        // Obtener todas las playas desde MySQL
        List<Beach> beaches = repository.findAll();

        // Convertir las playas a DTOs y añadir traducciones
        List<BeachDto> translatedBeaches = beaches.stream().map(beach -> {
            BeachDto beachDto = dtoConverter.convertDto(beach, BeachDto.class);

            // Buscar la traducción específica en MongoDB para esta playa
            String translationKey = "beach_" + beach.getId(); // Clave en MongoDB
            BeachTranslationMongoDB beachTranslationData = beachTranslationMongoService.findByKey(translationKey);

            if (beachTranslationData != null) {
                // Obtener la traducción de 'description' para el idioma requerido
                List<TranslatedLanguageMongoDb> descriptionTranslations = beachTranslationData.getTranslations().get("description");
                if (descriptionTranslations != null) {
                    TranslatedLanguageMongoDb translationLanguage = descriptionTranslations.stream()
                            .filter(lang -> lang.getId() != null && lang.getId().equals(requestedLanguage))
                            .findFirst()
                            .orElse(null);

                    if (translationLanguage != null) {
                        beachDto.setDescription(translationLanguage.getTranslate());
                    }
                }
            }

            return beachDto; // Retornar la playa con la descripción traducida
        }).toList();

        return translatedBeaches; // Lista de todas las playas con descripciones traducidas
    }


    @Override
    public List<BeachDto> findAll() {

        List<Beach> beaches = repository.findAll();

        return beaches.stream()
                .map(beach -> {

                    BeachDto beachDto = dtoConverter.convertDto(beach, BeachDto.class);

                    List<PhotoDto> photoDtos = photoDtoConverter.entityListToDtoList(beach.getPhotos());

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

//        if (entity.getLocation() != null) {
//            Location location = dtoLocation.convertToEntityFromCreateDto(entity.getLocation(), Location.class);
//            entityToInsert.setLocation(location);
//        }

        return dtoConverter.convertDto(repository.save(entityToInsert), BeachDto.class);

    }


    public BeachDto saveWithTranslate(CreateBeachDto createBeachDto) {
        // Convertir la DTO de entrada en una entidad `Beach`
        Beach entityToInsert = dtoConverter.convertToEntityFromCreateDto(createBeachDto, Beach.class);

        // Configurar relaciones (tipos, usuarios, servicios, cámaras)
        if (createBeachDto.getTypes() != null) {
            List<TypeBeach> typeBeaches = dtoTypeBeach.convertToEntityListFromDto(createBeachDto.getTypes(), TypeBeach.class);
            entityToInsert.setTypes(typeBeaches);
        }

        if (createBeachDto.getUsersInCharge() != null) {
            List<BeachManager> managers = dtoBeachManager.convertToEntityListFromCreateDto(createBeachDto.getUsersInCharge(), BeachManager.class);
            for (BeachManager manager : managers) {
                manager.setBeach(entityToInsert);
            }
            entityToInsert.setUsersInCharge(managers);
        }

        if (createBeachDto.getBeachHasServiceBeach() != null) {
            List<BeachHasService> services = dtoBeachHasService.convertToEntityListFromCreateDto(createBeachDto.getBeachHasServiceBeach(), BeachHasService.class);
            for (BeachHasService service : services) {
                service.setBeach(entityToInsert);
            }
            entityToInsert.setBeachHasServiceBeach(services);
        }

        if (createBeachDto.getCameras() != null) {
            List<Camera> cameras = dtoCamera.convertToEntityListFromCreateDto(createBeachDto.getCameras(), Camera.class);
            for (Camera camera : cameras) {
                camera.setBeach(entityToInsert);
            }
            entityToInsert.setCameras(cameras);
        }

        // Guardar la playa en MySQL
        Beach savedEntity = repository.save(entityToInsert);

        // Crear las traducciones en MongoDB
        beachTranslationMongoService.createTranslationsInMongoForLanguages(savedEntity, List.of("de", "en", "es"));

        // Convertir la entidad guardada en DTO y devolverla
        return dtoConverter.convertDto(savedEntity, BeachDto.class);
    }



    public BeachDto updateWithTranslate(Long id, CreateBeachDto entity) {
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

    public void deleteTranslate(Long id) {
        // Eliminar el registro en MySQL
        Beach beach = repository.findById(id).orElseThrow(() -> new NotFoundException(Beach.class, id));
        repository.delete(beach);

        // Eliminar el documento relacionado en MongoDB
        String mongoKey = "beach_" + id; // Construir la clave utilizada en MongoDB
        beachTranslationMongoService.deleteByKey(mongoKey); // Llama al servicio de Mongo para eliminar el documento
    }
}