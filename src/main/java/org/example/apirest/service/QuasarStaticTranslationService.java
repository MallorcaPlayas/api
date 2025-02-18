package org.example.apirest.service;

import lombok.extern.slf4j.Slf4j;
import org.example.apirest.model.TranslationMongoDB;
import org.example.apirest.repository.TranslatorMongoRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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

    public void saveTranslation(String language, String name, Map<String, Object> translations) {
        TranslationMongoDB existingTranslation = repository.findById(language).orElse(null);

        if (existingTranslation == null) {
            // Crear nueva entrada si no existe
            existingTranslation = new TranslationMongoDB();
            existingTranslation.setLanguage(language);
            existingTranslation.setName(name);
        }

        // Actualizar traducciones
        existingTranslation.setTranslations(translations);
        repository.save(existingTranslation);
    }

    public List<Map<String, String>> getAllLanguageIds() {
        return repository.findAll().stream()
                .map(lang -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("id", lang.getLanguage());
                    map.put("name", lang.getName());
                    return map;
                })
                .collect(Collectors.toList());
    }

    public boolean deleteLanguage(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }



    public boolean updateTranslation(String id, TranslationMongoDB updatedLanguage) {
        Optional<TranslationMongoDB> existing = repository.findById(id);

        if (existing.isPresent()) {
            TranslationMongoDB existingTranslation = existing.get();

            // Mantener el ID original para evitar que se cree un nuevo documento
            existingTranslation.setTranslations(updatedLanguage.getTranslations());

            repository.save(existingTranslation);
            return true;
        }
        return false;
    }
}
