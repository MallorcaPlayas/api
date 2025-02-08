package org.example.apirest.model.beach;


import lombok.Data;
import org.example.apirest.model.LanguageMongoDb;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document(collection = "beaches")
public class BeachTranslationMongoDB {
    @Id
    String key;
    String value;
    private Map<String, List<LanguageMongoDb>> translations;

}
