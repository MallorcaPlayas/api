package org.example.apirest.repository.beach;

import org.example.apirest.model.beach.BeachTranslation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BeachTranslationRepository extends MongoRepository<BeachTranslation, String> {
    BeachTranslation findByBeachId(String beachId);
}
