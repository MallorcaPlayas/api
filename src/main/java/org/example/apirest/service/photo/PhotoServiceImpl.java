package org.example.apirest.service.photo;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Photo;
import org.example.apirest.repository.PhotoRepository;
import org.example.apirest.service.s3.S3Service;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl{

    private static final String PUBLIC_BUCKET = "mallorca-playas-public";
    private static final String PRIVATE_BUCKET = "mallorca-playas-private";

    private final S3Service s3Service;

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

    public PhotoDto findOne(Long id) {
        Photo photo = super.repository.findById(id).orElse(null);
        PhotoDto photoDto = super.dtoConverter.convertDto(photo, PhotoDto.class);
        photoDto.setUrl(this.s3Service.urlGenerator(photo.getBucket(), photo.getPath()));
        return photoDto;
    }

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
