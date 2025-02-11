package org.example.apirest.service.photo;

import lombok.SneakyThrows;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.photo.DtoConverterPhoto;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.example.apirest.model.Photo;
import org.example.apirest.repository.PhotoRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.service.s3.S3Service;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoServiceImpl extends GeneralizedServiceImpl<Photo, PhotoDto, CreatePhotoDto, PhotoRepository> {

    private static final String PUBLIC_BUCKET = "mallorca-playas-public";
    private static final String PRIVATE_BUCKET = "mallorca-playas-private";


    private final S3Service s3Service;
    private final DtoConverterPhoto dtoConverterPhoto;

    public PhotoServiceImpl(PhotoRepository repository,
                            DtoConverterImpl<Photo, PhotoDto, CreatePhotoDto> dtoConverter,
                            S3Service s3Service,
                            DtoConverterPhoto dtoConverterPhoto) {
        super(repository, dtoConverter, Photo.class, PhotoDto.class);
        this.s3Service = s3Service;
        this.dtoConverterPhoto = dtoConverterPhoto;
    }

    @Override
    public PhotoDto findOne(Long id) {
        Photo photo = super.repository.findById(id).orElse(null);
        PhotoDto photoDto = dtoConverterPhoto.convertDto(photo, PhotoDto.class);
        if (photo.getIsPrivate()) {
            photoDto.setUrl(this.s3Service.generateTeamporalUrl(photo.getBucket(), photo.getPath()));
        }

        return photoDto;
    }

    @Override
    public List<PhotoDto> findAll() {
        List<Photo> photos = super.repository.findAll();
        return photos.stream().map(photo -> {
            PhotoDto photoDto = super.dtoConverter.convertDto(photo, PhotoDto.class);
            if (photo.getIsPrivate()) {
                photoDto.setUrl(this.s3Service.generateTeamporalUrl(photo.getBucket(), photo.getPath()));
            }
            return photoDto;
        }).toList();
    }

    @Override
    @SneakyThrows
    public PhotoDto save(CreatePhotoDto entity) {
        String bucket = entity.getIsPrivate() ? PRIVATE_BUCKET : PUBLIC_BUCKET;
        Photo photo = super.dtoConverter.convertToEntityFromCreateDto(entity, Photo.class);
        photo.setBucket(bucket);
        photo.setPath("");
        String fileName = s3Service.uploadFile(bucket, photo.getPath(), entity.getFile());
        photo.setPath(fileName);
        if (!photo.getIsPrivate()) photo.setUrl(s3Service.generateUrl(bucket, fileName));
        return dtoConverterPhoto.convertDto(super.repository.save(photo), PhotoDto.class);
    }

    @Override
    public void delete(Long id) {
        Photo photo = super.repository.findById(id).orElse(null);
        if(photo != null){
            s3Service.deleteFile(photo.getBucket(), photo.getPath());
            super.repository.deleteById(id);
        }
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
