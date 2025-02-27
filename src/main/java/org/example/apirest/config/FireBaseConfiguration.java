package org.example.apirest.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Configuration
public class FireBaseConfiguration {

    @Value("${firebase.private.key}")
    private Resource privateKey;

    @Bean
    @SneakyThrows
    public Firestore firestore() {
        InputStream credentials = new ByteArrayInputStream(privateKey.getContentAsByteArray());
        FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(credentials))
                .build();

        FirebaseApp firebaseApp = FirebaseApp.initializeApp(firebaseOptions);
        return FirestoreClient.getFirestore(firebaseApp);
    }
}
