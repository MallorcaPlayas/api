package org.example.apirest.controller.MigracionMysqToMongo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MigracionMysqlToMongoController {
    private final MigrateMysqToMongoService migrateMysqToMongoService;

    @GetMapping("/migrateAll")
    public String migrateData(@RequestParam String tableName) {
        boolean success = migrateMysqToMongoService.migrateTable(tableName);
        return success ? "Datos migrados exitosamente de MySQL a MongoDB." : "Error en la migración.";
    }

    @GetMapping("/migrateNotification")
    public String migrateTableNotification() {
        String tableName = "notifications";
        boolean success = migrateMysqToMongoService.migrateTableNotifications(tableName);
        return success ? "Datos migrados exitosamente de MySQL a MongoDB." : "Error en la migración.";
    }


    @GetMapping("/translate")
    public String translateData(
            @RequestParam String tableName,
            @RequestParam String targetLanguage) {

        boolean success = migrateMysqToMongoService.translateTableData(tableName, targetLanguage);

        return success ? "Traducciones al " + targetLanguage + " completadas."
                : "Error en la traducción.";
    }


}