package org.example.apirest.model.beach;
import lombok.Data;
import org.example.apirest.model.LanguageMongoDb;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document(collection = "beach_translations")
public class BeachTranslation {
    @Id
    private String id; // Este será el id del beach, puede ser el mismo ID de MySQL
    private String beachId; // ID del beach en MySQL para referenciarlo
    private List<LanguageMongoDb> translations; // Lista que se Este se usará para almacenar los idiomas y las traducciones.
}
