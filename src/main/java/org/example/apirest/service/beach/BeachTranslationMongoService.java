package org.example.apirest.service.beach;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apirest.model.LanguageMongoDb;
import org.example.apirest.model.beach.Beach;
import org.example.apirest.model.beach.BeachTranslationMongoDB;
import org.example.apirest.repository.beach.BeachTranslationMongoRepository;
import org.example.apirest.service.TraductorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j // Sirve para poder hacer logs y verlos en consola
@Service
@RequiredArgsConstructor
public class BeachTranslationMongoService {

    private final BeachTranslationMongoRepository beachTranslationMongoRepository;
    private final TraductorService traductorService;


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

    public void createTranslationsInMongoForEnglishAndGerman(Beach savedEntity) {

        String beachId = "beach_" + savedEntity.getId();



        // Crear la estructura de traducciones
        BeachTranslationMongoDB beachTranslation = new BeachTranslationMongoDB();
        beachTranslation.setKey(beachId);
        beachTranslation.setValue("pending translation");

        // guardar la "traducción" en español
        LanguageMongoDb spanishTranslation = new LanguageMongoDb();
        spanishTranslation.setId("es");
        spanishTranslation.setTranslate(savedEntity.getDescription());

        // Traducción al inglés
        LanguageMongoDb englishTranslation = new LanguageMongoDb();
        englishTranslation.setId("en");
        // metodo para traducir en inglés
        englishTranslation.setTranslate(traductorService.translateText(savedEntity.getDescription(), "es", "en"));

        // Traducción al alemán
        LanguageMongoDb germanTranslation = new LanguageMongoDb();
        germanTranslation.setId("de");
        // metodo para traducir en alemán
        germanTranslation.setTranslate(traductorService.translateText(savedEntity.getDescription(), "es", "de"));

        // Configurar las traducciones para 'description'
        beachTranslation.setTranslations(Map.of(
                "description", List.of(spanishTranslation, englishTranslation, germanTranslation)
        ));

        // Guardar la traducción en MongoDB
        beachTranslationMongoRepository.save(beachTranslation);
    }
}
