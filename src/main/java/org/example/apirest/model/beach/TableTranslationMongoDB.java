package org.example.apirest.model.beach;


import lombok.Data;
import org.example.apirest.model.TranslatedLanguageMongoDb;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document(collection = "migrateAllTableFromMysql")
public class TableTranslationMongoDB {
    @Id
    String key;
    String value;
    private Map<String, List<TranslatedLanguageMongoDb>> translations;

}
