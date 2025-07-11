package org.example.apirest.service.beach;

import org.example.apirest.model.TranslatedLanguageMongoDb;
import org.example.apirest.model.beach.TableTranslationMongoDB;
import org.example.apirest.repository.beach.TableTranslationMongoRepository;
import org.example.apirest.service.TranslatorProvider;
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
    private final TableTranslationMongoRepository beachTranslationMongoRepository;
    private final TranslatorProvider traductorService;


    @Value("${spring.datasource.url}")
    private String mysqlUrl;

    @Value("${spring.datasource.username}")
    private String mysqlUser;

    @Value("${spring.datasource.password}")
    private String mysqlPassword;

    public BeachMigrationService(TableTranslationMongoRepository beachTranslationMongoRepository, TranslatorProvider traductorService) {
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
                TableTranslationMongoDB beachTranslation = new TableTranslationMongoDB();
                beachTranslation.setKey(id);
                beachTranslation.setValue("pending translation");

                // Agrega la traducción en español
                TranslatedLanguageMongoDb spanishTranslation = new TranslatedLanguageMongoDb();
                spanishTranslation.setId("es");
                spanishTranslation.setTranslate(description);

                // Agrega el campo 'description'
                beachTranslation.setTranslations(Map.of("description", List.of(spanishTranslation)));

                // Guarda el documento en MongoDB
                beachTranslationMongoRepository.save(beachTranslation);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void translateDescriptionsForAllBeachesToLanguage(String languageToTranslate) {
        // Recuperar todos los documentos desde MongoDB
        List<TableTranslationMongoDB> allBeaches = beachTranslationMongoRepository.findAll();

        for (TableTranslationMongoDB beach : allBeaches) {
            if (beach.getTranslations() != null && beach.getTranslations().containsKey("description")) {
                // Obtener la traducción en español
                List<TranslatedLanguageMongoDb> descriptions = beach.getTranslations().get("description");

                TranslatedLanguageMongoDb spanishDescription = descriptions.stream()
                        .filter(language -> "es".equals(language.getId())) // Encontrar la descripción en español
                        .findFirst()
                        .orElse(null);

                if (spanishDescription != null) {
                    String textToTranslate = spanishDescription.getTranslate();

                    // Traducir al inglés usando TraductorService
                    // TODO en la base de datos de Mysql las descripcones estan en ingles, pero deberian estar en español
                    // TODO por eso ahora el metodo translateText recibe como segundo parametro el origen de la descripcion
                    //  que esta en INGLES y como tercer parametrno el idioma al que se quiere traducir
                    String translatedText = traductorService.translateText(textToTranslate, "en", languageToTranslate);

                    if (translatedText != null && !translatedText.isEmpty()) {
                        // Crear el objeto para la traducción al inglés
                        TranslatedLanguageMongoDb englishDescription = new TranslatedLanguageMongoDb();
                        englishDescription.setId(languageToTranslate);
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
