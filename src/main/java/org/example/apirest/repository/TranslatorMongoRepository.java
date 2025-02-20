package org.example.apirest.repository;

import org.apache.el.stream.Optional;
import org.example.apirest.model.TranslationMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslatorMongoRepository extends MongoRepository<TranslationMongoDB, String> {
}
