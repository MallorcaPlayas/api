package org.example.apirest.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class TranslatedLanguageMongoDb {
    @Field("id") // Mapea el campo "id" de MongoDB a este atributo, sino luego sale null
    String id;

    @Field("translate") // Mapea el campo "translate" de MongoDB a este atributo
    String translate;
}

/* En mongo esta asi, y para poder mapearlo uso esta clase
* languages: [
        {
            id: 'en',
            translate: 'Traduccion en inglés'
        },
        {
            id: 'es',
            translate: 'Traduccion en español'
        },
        {
            id: 'fr',
            translate: 'Traduccion en frances'
        }
    ]
* */
