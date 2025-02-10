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
import java.util.stream.Collectors;

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


    public BeachDto findOneTranslate(Long id, String requestedLanguage) {
        Beach beach = repository.findById(id).orElseThrow(() -> new NotFoundException(Beach.class, id));
        BeachDto beachDto = dtoConverter.convertDto(beach, BeachDto.class);
        // Buscar la traducción específica en MongoDB para esta playa
        // Indico el id de la playa para buscar la traducción específica en MongoDB
        // todos los documentos en MongoDB tienen la clave 'beach_' + id
        BeachTranslationMongoDB beachTranslationData = beachTranslationMongoService.findByKey("beach_" + id);
        if (beachTranslationData != null) {
            // Obtener la traducción de 'description' para el idioma requerido (ejemplo: 'ger')
            List<LanguageMongoDb> descriptionTranslations = beachTranslationData.getTranslations().get("description");
            if (descriptionTranslations != null) {
                LanguageMongoDb translationLanguage = descriptionTranslations.stream()
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
                List<LanguageMongoDb> descriptionTranslations = beachTranslationData.getTranslations().get("description");
                if (descriptionTranslations != null) {
                    LanguageMongoDb translationLanguage = descriptionTranslations.stream()
                            .filter(lang -> lang.getId() != null && lang.getId().equals(requestedLanguage))
                            .findFirst()
                            .orElse(null);

                    if (translationLanguage != null) {
                        beachDto.setDescription(translationLanguage.getTranslate());
                    }
                }
            }

            return beachDto; // Retornar la playa con la descripción traducida
        }).collect(Collectors.toList());

        return translatedBeaches; // Lista de todas las playas con descripciones traducidas
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

        if (!old.getDescription().equals(entity.getDescription())) {
            beachTranslationMongoService.updateTranslationsInMongo(id, entity.getDescription());
        }

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

    public void deleteTranslate(Long id) {
        // Eliminar el registro en MySQL
        Beach beach = repository.findById(id).orElseThrow(() -> new NotFoundException(Beach.class, id));
        repository.delete(beach);

        // Eliminar el documento relacionado en MongoDB
        String mongoKey = "beach_" + id; // Construir la clave utilizada en MongoDB
        beachTranslationMongoService.deleteByKey(mongoKey); // Llama al servicio de Mongo para eliminar el documento
    }
}