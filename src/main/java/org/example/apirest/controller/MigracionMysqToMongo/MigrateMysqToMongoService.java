package org.example.apirest.controller.MigracionMysqToMongo;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.apirest.model.TranslatedLanguageMongoDb;
import org.example.apirest.model.beach.BeachTranslationMongoDB;
import org.example.apirest.repository.beach.BeachTranslationMongoRepository;
import org.example.apirest.service.TranslatorProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;



@Service
public class MigrateMysqToMongoService {
    private final BeachTranslationMongoRepository beachTranslationMongoRepository;
    private final TranslatorProvider traductorService;

    @Value("${spring.datasource.url}")
    private String mysqlUrl;

    @Value("${spring.datasource.username}")
    private String mysqlUser;

    @Value("${spring.datasource.password}")
    private String mysqlPassword;

    private static final Map<String, List<String>> TABLE_FIELDS = new HashMap<>();

    static {
        TABLE_FIELDS.put("beaches", Arrays.asList("id", "description"));
        TABLE_FIELDS.put("comments", Arrays.asList("id", "content"));
        TABLE_FIELDS.put("excursions", Arrays.asList("id", "name", "description"));
    }

    public MigrateMysqToMongoService(BeachTranslationMongoRepository beachTranslationMongoRepository, TranslatorProvider traductorService) {
        this.beachTranslationMongoRepository = beachTranslationMongoRepository;
        this.traductorService = traductorService;
    }

    public boolean migrateTable(String tableName) {
        if (!TABLE_FIELDS.containsKey(tableName)) {
            System.out.println("Tabla no configurada: " + tableName);
            return false;
        }

        List<String> fields = TABLE_FIELDS.get(tableName);
        String fieldString = String.join(", ", fields);

        try (Connection connection = DriverManager.getConnection(mysqlUrl, mysqlUser, mysqlPassword)) {
            String query = "SELECT " + fieldString + " FROM " + tableName;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String id = tableName + "_" + resultSet.getInt("id");

                BeachTranslationMongoDB document = new BeachTranslationMongoDB();
                document.setKey(id);
                document.setValue("pending translation");

                Map<String, List<TranslatedLanguageMongoDb>> translations = new HashMap<>();

                for (String field : fields) {
                    if (!field.equals("id")) {
                        String value = resultSet.getString(field);

                        TranslatedLanguageMongoDb translation = new TranslatedLanguageMongoDb();
                        translation.setId("es");
                        translation.setTranslate(value);

                        translations.put(field, Collections.singletonList(translation));
                    }
                }

                document.setTranslations(translations);
                beachTranslationMongoRepository.save(document);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
