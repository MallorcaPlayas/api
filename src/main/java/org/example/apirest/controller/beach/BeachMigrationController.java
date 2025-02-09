package org.example.apirest.controller.beach;

import lombok.RequiredArgsConstructor;
import org.example.apirest.service.beach.BeachMigrationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BeachMigrationController {
    private final BeachMigrationService beachMigrationService;

    @GetMapping("/migrate")
    public String migrateData() {
        beachMigrationService.migrateMySQLToMongo();
        return "Datos migrados exitosamente de MySQL a MongoDB.";
    }
}
