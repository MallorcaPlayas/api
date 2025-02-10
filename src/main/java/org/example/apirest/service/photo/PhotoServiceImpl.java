package org.example.apirest.service.photo;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.organization.CreateOrganizationDto;
import org.example.apirest.dto.organization.OrganizationDto;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Organization;
import org.example.apirest.model.Photo;
import org.example.apirest.repository.OrganizationRepository;
import org.example.apirest.repository.PhotoRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.service.s3.S3Service;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoServiceImpl extends GeneralizedServiceImpl<Photo, PhotoDto, CreatePhotoDto, PhotoRepository> {

    private static final String PUBLIC_BUCKET = "mallorca-playas-public";
    private static final String PRIVATE_BUCKET = "mallorca-playas-private";


    private final S3Service s3Service;

    public PhotoServiceImpl(PhotoRepository repository, DtoConverterImpl<Photo,PhotoDto,CreatePhotoDto> dtoConverter , S3Service s3Service) {
        super(repository, dtoConverter, Photo.class, PhotoDto.class);
        this.s3Service = s3Service;
    }

    @Override
    public PhotoDto findOne(Long id) {
        Photo photo = super.repository.findById(id).orElse(null);
        PhotoDto photoDto = super.dtoConverter.convertDto(photo, PhotoDto.class);
        photoDto.setUrl(this.s3Service.urlGenerator(photo.getBucket(), photo.getPath()));
        return photoDto;
    }

    @Override
    public List<PhotoDto> findAll(){
        List<Photo> photos  = super.repository.findAll();
        List<PhotoDto> photoDtos = new ArrayList<>();
        for(Photo photo : photos){
            PhotoDto photoDto = super.dtoConverter.convertDto(photo, PhotoDto.class);
            String url = this.s3Service.urlGenerator(photo.getBucket(), photo.getPath());
            photoDto.setUrl(url);
            photoDtos.add(photoDto);
        }
        return photoDtos;
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
