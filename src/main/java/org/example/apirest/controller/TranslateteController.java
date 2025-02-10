package org.example.apirest.controller;


import org.example.apirest.service.TranslatorProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/translator")
public class TranslateteController {

    private final TranslatorProvider translatorService;

    public TranslateteController(TranslatorProvider translatorService) {
        this.translatorService = translatorService;
    }

    // Ejemplo de uso en postman:
    // 127.0.0.1:8080/translator/translate?text=Hola&origen=es&translated=en
    @GetMapping("/translate")
    public String translate(
            @RequestParam String text,
            @RequestParam String origen,
            @RequestParam String translated) {
        return translatorService.translateText(text, origen, translated);
    }
}
