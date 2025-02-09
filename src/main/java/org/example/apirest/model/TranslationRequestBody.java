package org.example.apirest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class TranslationRequestBody {
    private String languageFrom;
    private String languageTo;
    private String text;

}
