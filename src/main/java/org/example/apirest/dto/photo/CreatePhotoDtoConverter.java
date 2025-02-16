package org.example.apirest.dto.photo;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.model.Photo;
import org.example.apirest.service.s3.S3Service;
import org.example.apirest.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreatePhotoDtoConverter implements DtoConverter<Photo, CreatePhotoDto> {

    private final ModelMapper mapper;
    private final S3Service s3Service;
    @Value("${public.bucket}")
    private String PUBLIC_BUCKET;
    @Value("${private.bucket}")
    private String PRIVATE_BUCKET;

    @Override
    public CreatePhotoDto entityToDto(Photo photo) {
        return mapper.map(photo, CreatePhotoDto.class);
    }

    @Override
    public List<CreatePhotoDto> entityListToDtoList(List<Photo> photos) {
        return photos.stream().map(this::entityToDto).toList();
    }

    @Override
    public Photo dtoToEntity(CreatePhotoDto photoDto) {
        Photo photo = mapper.map(photoDto,Photo.class);

        MultipartFile file = photoDto.getFile();

        String bucket = photoDto.isPrivate() ? PRIVATE_BUCKET : PUBLIC_BUCKET;

        photo.setBucket(bucket);

        photo.setPath(Utils.randomFileName(file));

        if(!photo.isPrivate()){
            String url = s3Service.generateUrl(photo.getBucket(), photo.getPath());
            photo.setUrl(url);
        }
        return photo;
    }

    @Override
    public List<Photo> dtoListToEntityList(List<CreatePhotoDto> photoDtos) {
        return photoDtos.stream().map(this::dtoToEntity).toList();
    }


    
}
