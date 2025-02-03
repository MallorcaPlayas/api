package org.example.apirest.repository;

import org.example.apirest.model.UserMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositoryMongoDB extends MongoRepository<UserMongoDB, String> {
}
