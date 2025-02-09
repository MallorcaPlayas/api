package org.example.apirest.controller.beach;

import lombok.RequiredArgsConstructor;
import org.example.apirest.service.beach.BeachMigrationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BeachMigrationController {
    private final BeachMigrationService beachMigrationService;

    // Sirve para coger todos los datos de la base de datos MySQL y pasarlos a la base de datos MongoDB
    // En este caso solo vamos a migrar las descripciones
    @GetMapping("/migrate")
    public String migrateData() {
        beachMigrationService.migrateMySQLToMongo();
        return "Datos migrados exitosamente de MySQL a MongoDB.";
    }

    // Segundo paso, despues de migrar haremos las traducciones de las descripciones al inglés o cualquier otro idioma
    // traduce del español a otro idioma
    // postman:
    // 127.0.0.1:8080/translate-descriptions?targetLanguage=de
    @GetMapping("/translate-descriptions")
    public String translateDescriptions(@RequestParam String targetLanguage) {
        beachMigrationService.translateDescriptionsToLanguage(targetLanguage);
        return "Traducciones al " + targetLanguage + " completadas.";
    }
}
