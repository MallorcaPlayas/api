package org.example.apirest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${aws.accessKeyId}")
    private String accessKey;

    @Value("${aws.secretAccessKey}")
    private String secretKey;

    @Value("${aws.sessionToken}") // Opcional para credenciales temporales
    private String sessionToken;

    @Value("${aws.region}")
    private String region;

    @Bean
    public S3Client s3Client() {
        // Para credenciales temporales
        AwsCredentials credentials = AwsSessionCredentials.create(
                accessKey,
                secretKey,
                sessionToken
        );
        System.out.println("he pasado por aqui");
        System.out.println(accessKey);
        System.out.println("Token");
        System.out.println(sessionToken);
        System.out.println("Secret");
        System.out.println(secretKey);

        // Para credenciales permanentes
        // AwsCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }
}
