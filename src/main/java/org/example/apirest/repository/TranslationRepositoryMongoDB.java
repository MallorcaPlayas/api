package org.example.apirest.repository;

import org.example.apirest.model.TranslationMongoDB;
import org.example.apirest.model.UserMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationRepositoryMongoDB extends MongoRepository<TranslationMongoDB, String> {
}
