package org.example.apirest.service;


import org.example.apirest.model.LanguageMongoDb;
import org.example.apirest.model.Lenguaje;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TraductorService {

    private final String baseUrl = "https://theteacher.codiblau.com";

    public String translateText(String text, String origen, String translated) {
        System.out.println("Traduciendo: " + text + " de " + origen + " a " + translated);

        if (text == null || text.isEmpty()) {
            return "";
        }
        if (origen.equals(translated)) {
            return text;
        }

        String endpoint = "/public/google/translate";
        String url = baseUrl + endpoint;

        // Crear el cuerpo de la solicitud
        TranslationRequestBody requestBody = new TranslationRequestBody(origen, translated, text);

        // Configurar los encabezados
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Crear la solicitud HTTP
        HttpEntity<TranslationRequestBody> request = new HttpEntity<>(requestBody, headers);

        // Enviar la solicitud usando RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, request, String.class);
    }

    // Clase interna para representar el cuerpo de la solicitud
    private static class TranslationRequestBody {
        private String languageFrom;
        private String languageTo;
        private String text;

        public TranslationRequestBody(String languageFrom, String languageTo, String text) {
            this.languageFrom = languageFrom;
            this.languageTo = languageTo;
            this.text = text;
        }

        public String getLanguageFrom() {
            return languageFrom;
        }

        public void setLanguageFrom(String languageFrom) {
            this.languageFrom = languageFrom;
        }

        public String getLanguageTo() {
            return languageTo;
        }

        public void setLanguageTo(String languageTo) {
            this.languageTo = languageTo;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
