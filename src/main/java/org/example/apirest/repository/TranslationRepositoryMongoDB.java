package org.example.apirest.repository;

import org.example.apirest.model.TranslationMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository; //  Es una interfaz proporcionada por Spring Data MongoDB para realizar operaciones CRUD sobre la base de datos MongoDB.
import org.springframework.stereotype.Repository;

@Repository
// MongoDB generalmente utiliza String para los identificadores.
public interface TranslationRepositoryMongoDB extends MongoRepository<TranslationMongoDB, String> {
}
