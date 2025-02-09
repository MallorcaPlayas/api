package org.example.apirest.service.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class S3Service {

    public final S3Client s3Client;

    public void uploadFile(String bucketName, String key, String filePath) {
        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build(),
                RequestBody.fromFile(Paths.get(filePath))
        );
    }

    public void downloadFile(String bucketName, String key, String downloadPath) {
        s3Client.getObject(
                GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build(),
                Paths.get(downloadPath)
        );
    }

    public void deleteFile(String bucketName, String key) {
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build());
    }
}
