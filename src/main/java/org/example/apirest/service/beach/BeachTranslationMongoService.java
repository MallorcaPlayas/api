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

    private LanguageMongoDb createLanguageTranslation(String text, String languageCode) {
        LanguageMongoDb translation = new LanguageMongoDb();
        translation.setId(languageCode);

        // TODO doy por supuesto que el idioma origen es español
        translation.setTranslate(languageCode.equals("es") ? text : traductorService.translateText(text, "es", languageCode));
        return translation;
    }

    public void updateTranslationsInMongo(Long beachId, String newDescription) {
        String mongoKey = "beach_" + beachId;

        // Recuperar el documento relacionado en MongoDB
        BeachTranslationMongoDB mongoTranslation = beachTranslationMongoRepository.findByKey(mongoKey);

        if (mongoTranslation != null) {
            // Actualizar la traducción en español
            List<LanguageMongoDb> descriptions = mongoTranslation.getTranslations().get("description");
            if (descriptions != null) {
                LanguageMongoDb spanishTranslation = descriptions.stream()
                        .filter(lang -> "es".equals(lang.getId()))
                        .findFirst()
                        .orElse(null);

                if (spanishTranslation != null) {
                    spanishTranslation.setTranslate(newDescription);
                } else {
                    // Crear una nueva traducción en español si no existe
                    spanishTranslation = new LanguageMongoDb();
                    spanishTranslation.setId("es");
                    spanishTranslation.setTranslate(newDescription);
                    descriptions.add(spanishTranslation);
                }

                // Actualizar las traducciones en otros idiomas
                LanguageMongoDb englishTranslation = descriptions.stream()
                        .filter(lang -> "en".equals(lang.getId()))
                        .findFirst()
                        .orElse(null);
                if (englishTranslation != null) {
                    englishTranslation.setTranslate(traductorService.translateText(newDescription, "es", "en"));
                } else {
                    englishTranslation = new LanguageMongoDb();
                    englishTranslation.setId("en");
                    englishTranslation.setTranslate(traductorService.translateText(newDescription, "es", "en"));
                    descriptions.add(englishTranslation);
                }

                LanguageMongoDb germanTranslation = descriptions.stream()
                        .filter(lang -> "de".equals(lang.getId()))
                        .findFirst()
                        .orElse(null);
                if (germanTranslation != null) {
                    germanTranslation.setTranslate(traductorService.translateText(newDescription, "es", "de"));
                }else {
                    germanTranslation = new LanguageMongoDb();
                    germanTranslation.setId("de");
                    germanTranslation.setTranslate(traductorService.translateText(newDescription, "es", "de"));
                    descriptions.add(germanTranslation);

                    System.out.println("He traducido correctamente al aleman? " + germanTranslation.getTranslate());
                }

                // Guardar los cambios en MongoDB
                mongoTranslation.getTranslations().put("description", descriptions);
                beachTranslationMongoRepository.save(mongoTranslation);

            }
        } else {
            // Si no existe el documento en MongoDB, crearlo
            BeachTranslationMongoDB newTranslation = new BeachTranslationMongoDB();
            newTranslation.setKey(mongoKey);
            newTranslation.setValue("updated translation");

            LanguageMongoDb spanishTranslation = new LanguageMongoDb();
            spanishTranslation.setId("es");
            spanishTranslation.setTranslate(newDescription);

            LanguageMongoDb englishTranslation = new LanguageMongoDb();
            englishTranslation.setId("en");
            englishTranslation.setTranslate(traductorService.translateText(newDescription, "es", "en"));

            LanguageMongoDb germanTranslation = new LanguageMongoDb();
            germanTranslation.setId("de");
            germanTranslation.setTranslate(traductorService.translateText(newDescription, "es", "de"));

            newTranslation.setTranslations(Map.of(
                    "description", List.of(spanishTranslation, englishTranslation, germanTranslation)
            ));

            // Guardar el nuevo documento en MongoDB
            beachTranslationMongoRepository.save(newTranslation);
        }
    }
}
