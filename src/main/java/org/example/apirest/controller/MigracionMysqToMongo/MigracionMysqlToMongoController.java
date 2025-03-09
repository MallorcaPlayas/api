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
}