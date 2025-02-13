package org.example.apirest.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apirest.model.TranslationRequestBody;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.HtmlUtils;

import java.nio.file.Paths;
import java.util.HashMap;
import java.io.File;
import java.util.Map;

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
        String response = restTemplate.postForObject(url, request, String.class);

        //  Desescapar caracteres HTML antes de devolver la respuesta
        return HtmlUtils.htmlUnescape(response);
    }

    public Map<String, Object>  translateJsonAsText(Map<String, Object> json, String origen, String translated) {
        if (json == null || json.isEmpty()) {
            return new HashMap<>(); // Retornar un JSON vacío en formato Map
        }


        try {
            Map<String, Object> translatedJson = translateValues(json, origen, translated);

            // Devolver el JSON traducido directamente como un Map

            // TODO: pensar en donde lo voy a guardar para que me lo pueda coger 
            //  Quasar
            String directoryPath = "src/main/java/org/example/apirest/ABorrar";
            String fileName = "translated_" + origen.toUpperCase() + "_to_" + translated.toUpperCase() + ".json";

            // Asegurar que la carpeta existe
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs(); // Crea la carpeta si no existe
            }

            // Crear el archivo JSON
            File jsonFile = Paths.get(directoryPath, fileName).toFile();

            // Guardar el JSON traducido en el archivo
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, translatedJson);

            System.out.println("Archivo JSON guardado en: " + jsonFile.getAbsolutePath());

            return translatedJson;
        } catch (Exception e) {
            throw new RuntimeException("Error al serializar el JSON traducido", e);
        }
    }

    /**
     * Recorre un JSON recursivamente y traduce solo los valores, dejando las claves intactas.
     */
    private Map<String, Object> translateValues(Map<String, Object> json, String origen, String translated) {

        for (Map.Entry<String, Object> entry : json.entrySet()) {
            if (entry.getValue() instanceof String) {
                // Desescapar caracteres HTML en cada traducción
                entry.setValue(HtmlUtils.htmlUnescape(translateText((String) entry.getValue(), origen, translated)));

                //Si el valor es otro Map (anidado), llama recursivamente al mismo méto_do para traducirlo también.
            } else if (entry.getValue() instanceof Map) {
                entry.setValue(translateValues((Map<String, Object>) entry.getValue(), origen, translated));
            }
        }
        return json;
    }


}
