package org.example.apirest.repository.beach;

import org.example.apirest.model.beach.TableTranslationMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableTranslationMongoRepository extends MongoRepository<TableTranslationMongoDB, String> {
    TableTranslationMongoDB findByKey(String key);

    List<TableTranslationMongoDB> findByKeyStartingWith(String s);
}
