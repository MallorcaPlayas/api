package org.example.apirest.repository;

import org.apache.el.stream.Optional;
import org.example.apirest.model.TranslationMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TranslatorMongoRepository extends MongoRepository<TranslationMongoDB, String> {

}
