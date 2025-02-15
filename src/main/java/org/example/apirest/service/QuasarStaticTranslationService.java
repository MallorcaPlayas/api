package org.example.apirest.service;

import org.example.apirest.model.TranslationMongoDB;
import org.example.apirest.repository.TranslatorMongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuasarStaticTranslationService {
    private final TranslatorMongoRepository repository;

    public QuasarStaticTranslationService(TranslatorMongoRepository repository) {
        this.repository = repository;
    }

    public TranslationMongoDB findByLanguage(String language) {
        TranslationMongoDB translation = repository.findById(language).orElse(null);

        // Si no encuentra "fr-FR", intenta buscar "fr"
        if (translation == null && language.contains("-")) {
            String fallbackLanguage = language.split("-")[0]; // modifca lo que se busca de "fr-FR" a "fr"
            translation = repository.findById(fallbackLanguage).orElse(null);
        }

        return translation;
    }

    public void saveTranslation(String language, Map<String, Object> translations) {
        TranslationMongoDB existingTranslation = repository.findById(language).orElse(null);

        if (existingTranslation == null) {
            // Crear nueva entrada si no existe
            existingTranslation = new TranslationMongoDB();
            existingTranslation.setLanguage(language);
        }

        // Actualizar traducciones
        existingTranslation.setTranslations(translations);
        repository.save(existingTranslation);
    }

    public List<String> getAllLanguageIds() {
        return repository.findAll().stream()
                .map(TranslationMongoDB::getLanguage)
                .collect(Collectors.toList());
    }
}
