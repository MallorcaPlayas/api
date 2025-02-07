package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.model.LanguageMongoDb;
import org.example.apirest.model.TranslationMongoDB;
import org.example.apirest.model.UserMongoDB;
import org.example.apirest.model.beach.Beach;
import org.example.apirest.repository.TranslationRepositoryMongoDB;
import org.example.apirest.service.TranslationServiceMongoDB;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor // Con esto hacemos la injeccion de independencias
public class TranslationControllerMongoDB {

    private final BeachServiceImpl beachService;
    private final TranslationServiceMongoDB translationServiceMongoDB;

    // TODO en mongo puedo guardar un Json con los idiomas y las traducciones
    @GetMapping("/tranduccionMongoDB")
    public ResponseEntity<String> desar(){
        TranslationMongoDB translationMongoDB = new TranslationMongoDB();

        translationMongoDB.setKey("Prueba3");

        LanguageMongoDb traduccioES = new LanguageMongoDb();
        traduccioES.setId("es");
        traduccioES.setTranslate("ordenador");

        LanguageMongoDb traduccioEN = new LanguageMongoDb();
        traduccioEN.setId("en");
        traduccioEN.setTranslate("pc");

        LanguageMongoDb traduccioCat = new LanguageMongoDb();
        traduccioCat.setId("cat");
        traduccioCat.setTranslate("ordenador");



        List<LanguageMongoDb> idiomas = new ArrayList<>();
        idiomas.add(traduccioES);
        idiomas.add(traduccioEN);
        idiomas.add(traduccioCat);

        translationMongoDB.setLanguages(idiomas);


        translationServiceMongoDB.save(translationMongoDB);

        return ResponseEntity.ok("Traduccion guardad correctamente");
    }

    @GetMapping("/list")
    public ResponseEntity<List<TranslationMongoDB>> list(){

        return ResponseEntity.ok(translationServiceMongoDB.findAll());
    }

    // TODO este metodo lo tengo que poner en el controlador de beach
    // en postman: 127.0.0.1:8080/beach/1/translated?language=en
    @GetMapping("/beach/{id}/translated")
    public ResponseEntity<Beach> getBeachWithTranslation(
            @PathVariable Long id,
            @RequestParam String language) {


        Optional<Beach> beach = beachService.findBeachWithTranslation(id, language);

        if (beach.isPresent()) {
            return ResponseEntity.ok(beach.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
