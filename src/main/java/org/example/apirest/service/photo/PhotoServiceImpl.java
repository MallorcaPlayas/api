package org.example.apirest.service.photo;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.dto.photo.DtoConverterPhoto;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.example.apirest.model.Photo;
import org.example.apirest.repository.PhotoRepository;
import org.example.apirest.service.s3.S3Service;
import org.example.apirest.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl{

    @Value("${public.bucket}")
    private String PUBLIC_BUCKET;
    @Value("${private.bucket}")
    private String PRIVATE_BUCKET;

    private final S3Service s3Service;
    private final DtoConverter<Photo,PhotoDto,CreatePhotoDto> dtoConverter;
    private final PhotoRepository repository;
    
    public PhotoDto findOne(Long id) {
        Photo photo = repository.findById(id).orElse(null);
        return dtoConverter.entityToDto(photo);
    }
    
    public List<PhotoDto> findAll() {
        List<Photo> photos = repository.findAll();
        return dtoConverter.entityListToDtoList(photos);
    }
    
    @SneakyThrows
    public PhotoDto save(CreatePhotoDto entity) {
        Photo photo = dtoConverter.createDtoToEntity(entity);

        MultipartFile file = entity.getFile();

        String bucket = entity.isPrivate() ? PRIVATE_BUCKET : PUBLIC_BUCKET;

        photo.setBucket(bucket);

        photo.setPath(randomFileName(file));

        if(!photo.isPrivate()){
            String url = s3Service.generateUrl(photo.getBucket(), photo.getPath());
            photo.setUrl(url);
        }

        repository.save(photo);

        s3Service.uploadFile(bucket, photo.getPath(), file);

        return dtoConverter.entityToDto(photo);
    }
    
    public void delete(Long id) {
        Photo photo = repository.findById(id).orElse(null);
        if(photo != null){
            repository.deleteById(id);
            s3Service.deleteFile(photo.getBucket(), photo.getPath());
        }
    }

    private String randomFileName(MultipartFile file){
        String originalFileName = file.getOriginalFilename();
        String uniqueName = UUID.randomUUID().toString();
        String extension = Utils.extractExtension(originalFileName);
        return uniqueName + extension;
    }

    //    public PhotoDto uploadPublic(MultipartFile file) throws IOException {
//        return upload(PUBLIC_BUCKET , file);
//    }
//
//    public PhotoDto uploadPrivate(MultipartFile file) throws IOException {
//        return upload(PRIVATE_BUCKET , file);
//    }

//    @Override
//    public PhotoDto findOne(Long id) {
//        Photo photo = repository.findById(id).orElse(null);
//
//        PhotoDto photoDto = dtoConverter.convertDto(photo, PhotoDto.class);
//
//        photoDto.setUrl(s3Service.urlGenerator(photo.getBucket(), photo.getPath()));
//
//        return photoDto;
//    }
//
//
//
//
//    public PhotoDto getPrivate(Long id) throws IOException {
//        Photo photo = repository.findById(id).orElse(null);
//
//        if(photo == null) return null;
//
//        PhotoDto photoDto = dtoConverter.convertDto(photo, PhotoDto.class);
//        photoDto.setUrl(s3Service.temporalUrlGenerator(photo.getBucket() , photo.getPath()));
//
//        return photoDto;
//    }
//
//    private PhotoDto upload(String bucket , MultipartFile file) throws IOException {
//        String fileNameS3 = s3Service.uploadFile(bucket , "" , file);
//
////        Photo photo = new Photo(null,bucket,fileNameS3,null,null,null);
//
////        PhotoDto photoDto = dtoConverter.convertDto(repository.save(photo), PhotoDto.class);
//
////        photoDto.setUrl(s3Service.urlGenerator(photo.getBucket() , photo.getPath()));
//
////        return photoDto;
//        return null;
//    }
}
