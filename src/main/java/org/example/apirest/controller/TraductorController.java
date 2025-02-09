package org.example.apirest.controller;


import org.example.apirest.model.LanguageMongoDb;
import org.example.apirest.model.Lenguaje;
import org.example.apirest.service.TraductorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/translator")
public class TraductorController {

    private final TraductorService translatorService;

    public TraductorController(TraductorService translatorService) {
        this.translatorService = translatorService;
    }

    @GetMapping("/translate")
    public String translate(
            @RequestParam String text,
            @RequestParam String origen,
            @RequestParam String translated) {
        return translatorService.translateText(text, origen, translated);
    }
}
