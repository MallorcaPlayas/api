package org.example.apirest.service.beach;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apirest.model.beach.BeachTranslationMongoDB;
import org.example.apirest.repository.beach.BeachTranslationMongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j // Sirve para poder hacer logs y verlos en consola
@Service
@RequiredArgsConstructor
public class BeachTranslationMongoService {

    private final BeachTranslationMongoRepository beachTranslationMongoRepository;


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
}
