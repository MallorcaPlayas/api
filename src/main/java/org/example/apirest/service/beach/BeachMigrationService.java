package org.example.apirest.service.beach;

import lombok.RequiredArgsConstructor;
import org.example.apirest.model.LanguageMongoDb;
import org.example.apirest.model.beach.BeachTranslationMongoDB;
import org.example.apirest.repository.beach.BeachTranslationMongoRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Value;
import java.util.List;
import java.util.Map;

@Service
public class BeachMigrationService {
    private final BeachTranslationMongoRepository beachTranslationMongoRepository;


    @Value("${spring.datasource.url}")
    private String mysqlUrl;

    @Value("${spring.datasource.username}")
    private String mysqlUser;

    @Value("${spring.datasource.password}")
    private String mysqlPassword;

    public BeachMigrationService(BeachTranslationMongoRepository beachTranslationMongoRepository) {
        this.beachTranslationMongoRepository = beachTranslationMongoRepository;
    }

    public void migrateMySQLToMongo() {


        try (Connection connection = DriverManager.getConnection(mysqlUrl, mysqlUser, mysqlPassword)) {
            String query = "SELECT id, description FROM beaches";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String id = "beach_" + resultSet.getInt("id");
                String description = resultSet.getString("description");

                // Crea el documento para MongoDB
                BeachTranslationMongoDB beachTranslation = new BeachTranslationMongoDB();
                beachTranslation.setKey(id);
                beachTranslation.setValue("pending translation");

                // Agrega la traducción en español
                LanguageMongoDb spanishTranslation = new LanguageMongoDb();
                spanishTranslation.setId("es");
                spanishTranslation.setTranslate(description);

                // Agrega el campo 'description'
                beachTranslation.setTranslations(Map.of("description", List.of(spanishTranslation)));

                // Guarda el documento en MongoDB
                beachTranslationMongoRepository.save(beachTranslation);
            }

            System.out.println("Datos migrados exitosamente de MySQL a MongoDB.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
