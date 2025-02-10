package org.example.apirest.service.beach;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apirest.model.TranslatedLanguageMongoDb;
import org.example.apirest.model.beach.Beach;
import org.example.apirest.model.beach.BeachTranslationMongoDB;
import org.example.apirest.repository.beach.BeachTranslationMongoRepository;
import org.example.apirest.service.TranslatorProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j // Sirve para poder hacer logs y verlos en consola
@Service
@RequiredArgsConstructor
public class BeachTranslationMongoService {

    private final BeachTranslationMongoRepository beachTranslationMongoRepository;
    private final TranslatorProvider traductorService;


    public void save(BeachTranslationMongoDB translationMongoDB) {
        log.info("Saving translation" + translationMongoDB.getKey());
        beachTranslationMongoRepository.save(translationMongoDB);
    }

    public List<BeachTranslationMongoDB> findAll() {
        return beachTranslationMongoRepository.findAll();
    }

    public BeachTranslationMongoDB findByKey(String key) {
        return beachTranslationMongoRepository.findByKey(key);
    }

    public void deleteByKey(String key) {
        BeachTranslationMongoDB document = beachTranslationMongoRepository.findByKey(key);
        if (document != null) {
            beachTranslationMongoRepository.delete(document);
        }
    }

    public void createTranslationsInMongoForLanguages(Beach savedEntity, List<String> languages) {

        String beachId = "beach_" + savedEntity.getId();

        // Crear la estructura de traducciones
        BeachTranslationMongoDB beachMongo = new BeachTranslationMongoDB();
        beachMongo.setKey(beachId);
        beachMongo.setValue("successful translation");

        // Generar traducciones para todos los idiomas
        List<TranslatedLanguageMongoDb> translations = languages.stream()
                .map(lang -> createLanguageTranslation(savedEntity.getDescription(), lang))
                .collect(Collectors.toList());

        // Configurar las traducciones para 'description'
        beachMongo.setTranslations(Map.of("description", translations));

        // Guardar la traducción en MongoDB
        beachTranslationMongoRepository.save(beachMongo);
    }


    public void updateTranslationsInMongo(Long beachId, String newDescription) {
        String mongoKey = "beach_" + beachId;

        // Recuperar el documento relacionado en MongoDB
        BeachTranslationMongoDB mongoTranslation = beachTranslationMongoRepository.findByKey(mongoKey);

        if (mongoTranslation != null) {
            // Actualizar el documento existente
            updateExistingTranslations(mongoTranslation, newDescription);
        } else {
            // Crear un nuevo documento si no existe
            createNewTranslations(mongoKey, newDescription);
        }
    }

    private void updateExistingTranslations(BeachTranslationMongoDB beachTranslationMongoDB, String newDescription) {
        List<TranslatedLanguageMongoDb> listDescriptions = beachTranslationMongoDB.getTranslations()
                .getOrDefault("description", List.of());

        // Actualizar o agregar traducciones
        updateOrAddTranslation(listDescriptions, "es", newDescription);
        updateOrAddTranslation(listDescriptions, "en", traductorService.translateText(newDescription, "es", "en"));
        updateOrAddTranslation(listDescriptions, "de", traductorService.translateText(newDescription, "es", "de"));

        // Guardar los cambios en MongoDB
        beachTranslationMongoDB.getTranslations().put("description", listDescriptions);
        beachTranslationMongoRepository.save(beachTranslationMongoDB);
    }

    private void createNewTranslations(String mongoKey, String newDescription) {
        BeachTranslationMongoDB newTranslation = new BeachTranslationMongoDB();
        newTranslation.setKey(mongoKey);
        newTranslation.setValue("updated translation");

        // Crear traducciones para español, inglés y alemán
        List<TranslatedLanguageMongoDb> translations = List.of(
                createLanguageTranslation2("es", newDescription),
                createLanguageTranslation2("en", traductorService.translateText(newDescription, "es", "en")),
                createLanguageTranslation2("de", traductorService.translateText(newDescription, "es", "de"))
        );

        newTranslation.setTranslations(Map.of("description", translations));

        // Guardar el nuevo documento en MongoDB
        beachTranslationMongoRepository.save(newTranslation);
    }

    private void updateOrAddTranslation(List<TranslatedLanguageMongoDb> descriptions, String languageCode, String translatedText) {
        TranslatedLanguageMongoDb translation = descriptions.stream()
                .filter(idLang -> languageCode.equals(idLang.getId()))
                .findFirst()
                .orElse(null);

        if (translation != null) {
            // Actualizar la traducción existente
            translation.setTranslate(translatedText);
        } else {
            // Agregar una nueva traducción
            descriptions.add(createLanguageTranslation2(languageCode, translatedText));
        }
    }

    private TranslatedLanguageMongoDb createLanguageTranslation(String text, String languageCode) {
        TranslatedLanguageMongoDb translation = new TranslatedLanguageMongoDb();
        translation.setId(languageCode);

        // TODO doy por supuesto que el idioma origen es español
        translation.setTranslate(languageCode.equals("es") ? text : traductorService.translateText(text, "es", languageCode));
        return translation;
    }

    private TranslatedLanguageMongoDb createLanguageTranslation2(String languageCode, String translatedText) {
        TranslatedLanguageMongoDb translation = new TranslatedLanguageMongoDb();
        translation.setId(languageCode);
        translation.setTranslate(translatedText);
        return translation;
    }

}
