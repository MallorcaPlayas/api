package org.example.apirest.repository;

import org.apache.el.stream.Optional;
import org.example.apirest.model.TranslationMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TranslatorMongoRepository extends MongoRepository<TranslationMongoDB, String> {
}
