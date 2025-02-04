package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.model.LanguageMongoDb;
import org.example.apirest.model.TranslationMongoDB;
import org.example.apirest.model.UserMongoDB;
import org.example.apirest.repository.TranslationRepositoryMongoDB;
import org.example.apirest.service.TranslationServiceMongoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor // Con esto hacemos la injeccion de independencias
public class TranslationControllerMongoDB {


    private final TranslationServiceMongoDB translationServiceMongoDB;

    // TODO en mongo puedo guardar un Json con los idiomas y las traducciones
    @GetMapping("/tranduccionMongoDB")
    public ResponseEntity<String> desar(){
        TranslationMongoDB translationMongoDB = new TranslationMongoDB();

        translationMongoDB.setKey("Prueba2");

        LanguageMongoDb traduccioES = new LanguageMongoDb();
        traduccioES.setId("es");
        traduccioES.setTranslate("mesa");

        LanguageMongoDb traduccioEN = new LanguageMongoDb();
        traduccioEN.setId("en");
        traduccioEN.setTranslate("table");

        LanguageMongoDb traduccioCat = new LanguageMongoDb();
        traduccioCat.setId("cat");
        traduccioCat.setTranslate("taula");



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
}
