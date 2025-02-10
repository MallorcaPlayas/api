package org.example.apirest.service.s3;

import lombok.RequiredArgsConstructor;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Utilities;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;
    private final S3Presigner presigner;


    rotected final R repository;

    @Override
    public List<Dto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), dtoClass);
    }

    @Override
    public Dto findOne(Long id) {
        Entity entity = repository.findById(id).orElseThrow(()-> new NotFoundException(entityClass,id));
        return dtoConverter.convertDto(entity, dtoClass);
    }

    @Override
    public Dto save(CreateDto entity) {
        Entity entityToInsert = dtoConverter.convertToEntityFromCreateDto(entity, entityClass);
        return dtoConverter.convertDto(repository.save(entityToInsert), dtoClass);
    }

    @Override
    public Dto update(Long id, CreateDto createEntity) {
        Entity oldEntity = repository.findById(id).orElseThrow(() -> new NotFoundException(entityClass, id));
        Entity entityToInsert = dtoConverter.convertToEntityFromCreateDto(createEntity, entityClass);

        if (oldEntity == null) {
            return null;
        }

        UtilsClass.updateFields(oldEntity, entityToInsert);

        return dtoConverter.convertDto(repository.save(oldEntity), dtoClass);
    }

    @Override
    public void delete(Long id) {
        Entity entity = repository.findById(id).orElseThrow(()-> new NotFoundException(entityClass,id));
        repository.delete(entity);
    }

    public String uploadFile(String bucketName, String prefix, MultipartFile file) throws IOException {
        String key = generateKey(prefix , file);
        String contentType = file.getContentType();
        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .contentType(contentType)
                        .key(key)
                        .build(),
                RequestBody.fromInputStream(file.getInputStream(), file.getSize())
        );
        return key;
    }

    public void deleteFile(String bucketName, String key) {
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build());
    }

    public String urlGenerator(String bucket , String key){
        S3Utilities s3Utilities = s3Client.utilities();

        URL url = s3Utilities.getUrl(GetUrlRequest.builder()
                .bucket(bucket)
                .key(key)
                .build());

        return url.toExternalForm();
    }

    public String temporalUrlGenerator(String bucket , String key){
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();


        PresignedGetObjectRequest presignedRequest = presigner.presignGetObject(r -> r
                .getObjectRequest(getObjectRequest)
                .signatureDuration(Duration.ofSeconds(10))
        );

        URL url = presignedRequest.url();

        presigner.close();

        return url.toExternalForm();
    }

    private String extractExtension(String fileName) {
        String extension = "";
        if (fileName != null && fileName.contains(".")) {
            extension = fileName.substring(fileName.lastIndexOf("."));
        }
        return extension;
    }

    private String generateKey(String prefix , MultipartFile file){
        String originalFileName = file.getOriginalFilename();
        String uniqueName = UUID.randomUUID().toString();
        String extension = extractExtension(originalFileName);
        return prefix + uniqueName + extension;
    }
}
