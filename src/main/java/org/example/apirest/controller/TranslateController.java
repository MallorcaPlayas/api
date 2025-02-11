package org.example.apirest.controller;


import org.example.apirest.service.TranslatorProvider;
import org.springframework.web.bind.annotation.*;

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
    public Map<String, Object> translateJsonAsText(@RequestBody Map<String, Object> json,
                                      @RequestParam String origen,
                                      @RequestParam String translated) {
        // Map<String, Object> sirve para convertir un JSON recibido en una estructura de datos manejable en Java
        // Aqui el Map<String, Object> json es un JSON que se envia en el cuerpo de la solicitud


        return translatorProvider.translateJsonAsText(json, origen, translated);
    }
}
