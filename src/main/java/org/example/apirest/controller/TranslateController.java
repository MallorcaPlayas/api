package org.example.apirest.controller;


import org.example.apirest.model.TranslationMongoDB;
import org.example.apirest.service.QuasarStaticTranslationService;
import org.example.apirest.service.TranslatorProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/translator")
@CrossOrigin(origins = "*")
public class TranslateController {

    private final QuasarStaticTranslationService quasarStaticTranslationService;
    private final TranslatorProvider translatorProvider;

    public TranslateController(QuasarStaticTranslationService translatorMongoService, TranslatorProvider translatorService) {
        this.quasarStaticTranslationService = translatorMongoService;
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
            @RequestParam String translated,
            @RequestParam String name) {

        Map<String, Object> translatedJson = translatorProvider.translateJsonAsText(json, origen, translated, name);


        return ResponseEntity.ok() // ResponseEntity.ok() → Devuelve un HTTP 200 (OK) si to_do salió bien.
                .contentType(MediaType.APPLICATION_JSON) // contentType(MediaType.APPLICATION_JSON) → Indica que el contenido de la respuesta es un JSON.
                .body(translatedJson); //.body(translatedJson) → Envía el JSON traducido en la respuesta.
    }


    @GetMapping("/getTranslatedJson")
    public ResponseEntity<Map<String, Object>> getTranslatedJson( @RequestParam String translated) {
        // Buscar traducción en MongoDB
        TranslationMongoDB translation = quasarStaticTranslationService.findByLanguage(translated);

        if (translation != null) {
            return ResponseEntity.ok(translation.getTranslations());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAvailableLanguages")
    public ResponseEntity<List<Map<String, String>>> getAvailableLanguages() {
        List<Map<String, String>> languages = quasarStaticTranslationService.getAllLanguageIds();
        return ResponseEntity.ok(languages);
    }

    @DeleteMapping("/deleteLanguage/{id}")
    public ResponseEntity<String> deleteLanguage(@PathVariable String id) {
        boolean deleted = quasarStaticTranslationService.deleteLanguage(id);
        if (deleted) {
            return ResponseEntity.ok("Idioma eliminado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Idioma no encontrado.");
        }
    }


}
