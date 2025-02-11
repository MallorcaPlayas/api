package org.example.apirest.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apirest.model.TranslationRequestBody;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.HtmlUtils;

@Service
public class TranslatorProvider {

    private final String baseUrl = "https://theteacher.codiblau.com";
    private final ObjectMapper objectMapper = new ObjectMapper();


    public String translateText(String text, String origen, String translated) {

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

    public String translateJsonAsText(Object json, String origen, String translated) {
        if (json == null) {
            return "";
        }

        String endpoint = "/public/google/translate";
        String url = baseUrl + endpoint;

        // Convertir JSON a String para enviarlo como texto plano
        String jsonString;
        try {
            jsonString = objectMapper.writeValueAsString(json);
        } catch (Exception e) {
            throw new RuntimeException("Error al serializar el JSON", e);
        }

        // Crear la solicitud con el JSON en formato de texto
        TranslationRequestBody requestBody = new TranslationRequestBody(origen, translated, jsonString);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TranslationRequestBody> request = new HttpEntity<>(requestBody, headers);

        // Llamar a la API y recibir la respuesta como String
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(url, request, String.class);

        // 🔥 DECODIFICAR caracteres HTML antes de devolver la respuesta
        return HtmlUtils.htmlUnescape(response);
    }


}
