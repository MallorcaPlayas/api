package org.example.apirest.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.apirest.service.TranslatorProvider;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/translator")
public class TranslateController {

    private final TranslatorProvider translatorProvider;

    public TranslateController(TranslatorProvider translatorService) {
        this.translatorProvider = translatorService;
    }

    // Ejemplo de uso en postman:
    // 127.0.0.1:8080/translator/translate?text=Hola&origen=es&translated=en
    @GetMapping("/translate")
    public String translate(
            @RequestParam String text,
            @RequestParam String origen,
            @RequestParam String translated) {
        return translatorProvider.translateText(text, origen, translated);
    }

    // http://localhost:8080/translator/translateJsonAsText?origen=es&translated=en
    @PostMapping("/translateJsonAsText")
    // ResponseEntity<Map<String, Object>> → Indica que el método devolverá un JSON en la respuesta.
    // Que es ResponseEntity ? → Es una clase de Spring que permite devolver un status code, headers, and body
    public ResponseEntity<Map<String, Object>> translateJson(
            @RequestBody Map<String, Object> json,
            @RequestParam String origen,
            @RequestParam String translated) {

        Map<String, Object> translatedJson = translatorProvider.translateJsonAsText(json, origen, translated);


        return ResponseEntity.ok() // ResponseEntity.ok() → Devuelve un HTTP 200 (OK) si to_do salió bien.
                .contentType(MediaType.APPLICATION_JSON) // contentType(MediaType.APPLICATION_JSON) → Indica que el contenido de la respuesta es un JSON.
                .body(translatedJson); //.body(translatedJson) → Envía el JSON traducido en la respuesta.
    }

    @GetMapping("/getTranslatedJson")
    public ResponseEntity<Map<String, Object>> getTranslatedJson(
            @RequestParam String origen,
            @RequestParam String translated) {

        String directoryPath = "src/main/java/org/example/apirest/ABorrar";
//        String fileName = "translated_" + origen.toUpperCase() + "_to_" + translated.toUpperCase() + ".json";
        String fileName;
        if ("es-ES".equalsIgnoreCase(translated)) {
            fileName = "jsonIndiceEspa.json";
        } else {
            fileName = "translated_" + origen.toUpperCase() + "_to_" + translated.toUpperCase() + ".json";
        }
        File jsonFile = new File(directoryPath, fileName); // Representa el archivo JSON que queremos leer.

        if (jsonFile.exists()) {
            try {
                // ObjectMapper es una libreria que sirve para convertir JSON a Map y viceversa
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> jsonData = objectMapper.readValue(jsonFile, Map.class); // (readValue) Convierte el JSON en un objecto Java
                return ResponseEntity.ok(jsonData);
            } catch (IOException e) {
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
