package org.example.apirest.repository.beach;

import org.example.apirest.model.beach.BeachTranslationMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeachTranslationMongoRepository extends MongoRepository<BeachTranslationMongoDB, String> {
    BeachTranslationMongoDB findByKey(String key);
}
