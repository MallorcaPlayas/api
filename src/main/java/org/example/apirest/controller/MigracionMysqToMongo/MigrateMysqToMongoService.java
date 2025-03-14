package org.example.apirest.controller.MigracionMysqToMongo;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.*;



@Service
public class MigrateMysqToMongoService {
    private final TableTranslationMongoRepository tableTranslationMongoRepository;
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
        TABLE_FIELDS.put("notifications", Arrays.asList("id", "data"));
    }

    public MigrateMysqToMongoService(TableTranslationMongoRepository beachTranslationMongoRepository, TranslatorProvider traductorService) {
        this.tableTranslationMongoRepository = beachTranslationMongoRepository;
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

                TableTranslationMongoDB document = new TableTranslationMongoDB();
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
                tableTranslationMongoRepository.save(document);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean migrateTableNotifications(String tableName) {
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
                String id = resultSet.getString("id"); // UUID como String

                TableTranslationMongoDB document = new TableTranslationMongoDB();
                document.setKey(tableName + "_" + id);
                document.setValue("pending translation");

                Map<String, List<TranslatedLanguageMongoDb>> translations = new HashMap<>();

                for (String field : fields) {
                    if (!field.equals("id")) {
                        String value = resultSet.getString(field);

                        // Si el campo es "data" en "notifications", guardamos el JSON como un String sin modificar
                        if (tableName.equals("notifications") && field.equals("data")) {
                            TranslatedLanguageMongoDb translation = new TranslatedLanguageMongoDb();
                            translation.setId("es"); // Idioma español
                            translation.setTranslate(value); // Guardamos el JSON sin modificar

                            translations.put("data", Collections.singletonList(translation));
                        }
                    }
                }

                document.setTranslations(translations);
                tableTranslationMongoRepository.save(document);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private Map<String, String> parseJson(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, new TypeReference<Map<String, String>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>(); // Si hay un error, devolvemos un mapa vacío
        }
    }

    public boolean translateTableData(String tableName, String languageToTranslate) {
        // Obtener documentos de la tabla especificada
        List<TableTranslationMongoDB> allDocuments = tableTranslationMongoRepository.findByKeyStartingWith(tableName + "_");

        if (allDocuments.isEmpty()) {
            System.out.println("No se encontraron documentos para la tabla: " + tableName);
            return false;
        }

        for (TableTranslationMongoDB document : allDocuments) {
            if (document.getTranslations() == null) continue;

            for (String field : document.getTranslations().keySet()) {
                List<TranslatedLanguageMongoDb> translations = document.getTranslations().get(field);

                // Buscar la traducción en español (original)
                TranslatedLanguageMongoDb spanishTranslation = translations.stream()
                        .filter(lang -> "es".equals(lang.getId()))
                        .findFirst()
                        .orElse(null);

                if (spanishTranslation != null) {
                    String textToTranslate = spanishTranslation.getTranslate();

                    // Traducir el texto al idioma deseado
                    String translatedText = traductorService.translateText(textToTranslate, "es", languageToTranslate);

                    if (translatedText != null && !translatedText.isEmpty()) {
                        // Buscar si ya existe la traducción en el idioma deseado
                        Optional<TranslatedLanguageMongoDb> existingTranslationOpt = translations.stream()
                                .filter(lang -> languageToTranslate.equals(lang.getId()))
                                .findFirst();

                        if (existingTranslationOpt.isPresent()) {
                            // Si la traducción ya existe, sobrescribirla
                            TranslatedLanguageMongoDb existingTranslation = existingTranslationOpt.get();
                            existingTranslation.setTranslate(translatedText);
                        } else {
                            // Si no existe, crear una nueva traducción
                            TranslatedLanguageMongoDb newTranslation = new TranslatedLanguageMongoDb();
                            newTranslation.setId(languageToTranslate);
                            newTranslation.setTranslate(translatedText);
                            translations.add(newTranslation);
                        }

                        // Guardar los cambios en MongoDB
                        document.getTranslations().put(field, translations);
                        tableTranslationMongoRepository.save(document);
                    }
                }
            }
        }
        return true;
    }



    public boolean translateTableNotification(String tableName, String languageToTranslate) {
        // Obtener documentos de la tabla especificada
        List<TableTranslationMongoDB> allDocuments = tableTranslationMongoRepository.findByKeyStartingWith(tableName + "_");

        if (allDocuments.isEmpty()) {
            System.out.println("No se encontraron documentos para la tabla: " + tableName);
            return false;
        }

        ObjectMapper objectMapper = new ObjectMapper(); // Para manipular JSON

        for (TableTranslationMongoDB document : allDocuments) {
            if (document.getTranslations() == null) continue;

            for (String field : document.getTranslations().keySet()) {
                List<TranslatedLanguageMongoDb> translations = document.getTranslations().get(field);

                // Buscar la traducción en español
                TranslatedLanguageMongoDb spanishTranslation = translations.stream()
                        .filter(lang -> "es".equals(lang.getId()))
                        .findFirst()
                        .orElse(null);

                if (spanishTranslation != null) {
                    String jsonText = spanishTranslation.getTranslate(); // JSON en String

                    try {
                        // Convertir el String JSON a un Map
                        Map<String, Object> jsonData = objectMapper.readValue(jsonText, new TypeReference<Map<String, Object>>() {});

                        boolean needsTranslation = false;

                        // Traducir todos los valores dentro del JSON
                        for (Map.Entry<String, Object> entry : jsonData.entrySet()) {
                            String key = entry.getKey();
                            String originalText = entry.getValue().toString();

                            // Verificar si ya hay una traducción en el idioma especificado
                            TranslatedLanguageMongoDb existingTranslation = translations.stream()
                                    .filter(lang -> languageToTranslate.equals(lang.getId()))
                                    .findFirst()
                                    .orElse(null);

                            if (existingTranslation != null) {
                                // Ya existe la traducción -> modificarla en lugar de añadir una nueva
                                Map<String, Object> existingJson = objectMapper.readValue(existingTranslation.getTranslate(), new TypeReference<Map<String, Object>>() {});
                                String translatedText = traductorService.translateText(originalText, "es", languageToTranslate);

                                if (!translatedText.isEmpty()) {
                                    existingJson.put(key, translatedText);
                                    existingTranslation.setTranslate(objectMapper.writeValueAsString(existingJson));
                                    needsTranslation = true;
                                }
                            } else {
                                // No existe una traducción -> crear una nueva
                                String translatedText = traductorService.translateText(originalText, "es", languageToTranslate);

                                if (!translatedText.isEmpty()) {
                                    TranslatedLanguageMongoDb newTranslation = new TranslatedLanguageMongoDb();
                                    newTranslation.setId(languageToTranslate);

                                    Map<String, Object> newJson = new HashMap<>();
                                    newJson.put(key, translatedText);

                                    newTranslation.setTranslate(objectMapper.writeValueAsString(newJson));
                                    translations.add(newTranslation);
                                    needsTranslation = true;
                                }
                            }
                        }

                        if (needsTranslation) {
                            document.getTranslations().put(field, translations);
                            tableTranslationMongoRepository.save(document);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error procesando JSON en el documento con ID: " + document.getKey());
                    }
                }
            }
        }
        return true;
    }



}
