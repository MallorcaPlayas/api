package org.example.apirest.service.beach;

import org.example.apirest.model.LanguageMongoDb;
import org.example.apirest.model.beach.BeachTranslationMongoDB;
import org.example.apirest.repository.beach.BeachTranslationMongoRepository;
import org.example.apirest.service.TraductorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Service
public class BeachMigrationService {
    private final BeachTranslationMongoRepository beachTranslationMongoRepository;
    private final TraductorService traductorService;


    @Value("${spring.datasource.url}")
    private String mysqlUrl;

    @Value("${spring.datasource.username}")
    private String mysqlUser;

    @Value("${spring.datasource.password}")
    private String mysqlPassword;

    public BeachMigrationService(BeachTranslationMongoRepository beachTranslationMongoRepository, TraductorService traductorService) {
        this.beachTranslationMongoRepository = beachTranslationMongoRepository;
        this.traductorService = traductorService;
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

    public void translateDescriptionsToEnglish() {
        // Recuperar todos los documentos desde MongoDB
        List<BeachTranslationMongoDB> allBeaches = beachTranslationMongoRepository.findAll();

        for (BeachTranslationMongoDB beach : allBeaches) {
            if (beach.getTranslations() != null && beach.getTranslations().containsKey("description")) {
                // Obtener la traducción en español
                List<LanguageMongoDb> descriptions = beach.getTranslations().get("description");

                LanguageMongoDb spanishDescription = descriptions.stream()
                        .filter(language -> "es".equals(language.getId())) // Encontrar la descripción en español
                        .findFirst()
                        .orElse(null);

                if (spanishDescription != null) {
                    String textToTranslate = spanishDescription.getTranslate();

                    // Traducir al inglés usando TraductorService
                    String translatedText = traductorService.translateText(textToTranslate, "es", "en");

                    if (translatedText != null && !translatedText.isEmpty()) {
                        // Crear el objeto para la traducción al inglés
                        LanguageMongoDb englishDescription = new LanguageMongoDb();
                        englishDescription.setId("en");
                        englishDescription.setTranslate(translatedText);

                        // Agregar la traducción al campo 'description'
                        descriptions.add(englishDescription);
                        beach.getTranslations().put("description", descriptions);

                        // Guardar los cambios en MongoDB
                        beachTranslationMongoRepository.save(beach);
                    }
                }
            }
        }
    }
}
