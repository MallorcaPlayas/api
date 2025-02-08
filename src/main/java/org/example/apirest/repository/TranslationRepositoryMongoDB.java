package org.example.apirest.repository;

import org.example.apirest.model.beach.BeachTranslationMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationRepositoryMongoDB extends MongoRepository<BeachTranslationMongoDB, String> {
    BeachTranslationMongoDB findByKey(String key);
}
