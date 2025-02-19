package org.example.apirest.service.photo;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.apirest.dto.DtoConverter;
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

    private final S3Service s3Service;
    private final DtoConverter<Photo,PhotoDto> dtoConverter;
    private final DtoConverter<Photo,CreatePhotoDto> createDtoConverter;
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
        Photo photo = createDtoConverter.dtoToEntity(entity);

        repository.save(photo);

        s3Service.uploadFile(photo.getBucket(), photo.getPath(), entity.getFile());

        return dtoConverter.entityToDto(photo);
    }
    
    public void delete(Long id) {
        Photo photo = repository.findById(id).orElse(null);
        if(photo != null){
            s3Service.deleteFile(photo.getBucket(), photo.getPath());
            repository.deleteById(id);
        }
    }
}
