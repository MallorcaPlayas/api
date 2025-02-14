package org.example.apirest.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Map;

@Data
@Document(collection = "translations")
public class TranslationMongoDB {
    @Id
    private String language;  // "es", "en", "fr"
    private Map<String, Object> translations;
}
