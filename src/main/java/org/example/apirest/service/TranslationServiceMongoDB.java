package org.example.apirest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apirest.model.beach.BeachTranslationMongoDB;
import org.example.apirest.repository.TranslationRepositoryMongoDB;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j // Sirve para poder hacer logs y verlos en consola
@Service
@RequiredArgsConstructor
public class TranslationServiceMongoDB {

    private final TranslationRepositoryMongoDB translationRepositoryMongoDB;


    public void save(BeachTranslationMongoDB translationMongoDB) {
        log.info("Saving translation" + translationMongoDB.getKey());
        translationRepositoryMongoDB.save(translationMongoDB);
    }

    public List<BeachTranslationMongoDB> findAll() {
        return translationRepositoryMongoDB.findAll();
    }

    public BeachTranslationMongoDB findByKey(String key) {
        return translationRepositoryMongoDB.findByKey(key);
    }
}
