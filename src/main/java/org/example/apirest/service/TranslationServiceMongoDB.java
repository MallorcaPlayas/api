package org.example.apirest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apirest.model.TranslationMongoDB;
import org.example.apirest.repository.TranslationRepositoryMongoDB;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j // Sirve para poder hacer logs y verlos en consola
@Service
@RequiredArgsConstructor
public class TranslationServiceMongoDB {

    private final TranslationRepositoryMongoDB translationRepositoryMongoDB;


    public void save(TranslationMongoDB translationMongoDB) {
        log.info("guardando traducciones" + translationMongoDB.getKey());
        translationRepositoryMongoDB.save(translationMongoDB);
    }

    public List<TranslationMongoDB> findAll() {
        return translationRepositoryMongoDB.findAll();
    }
}
