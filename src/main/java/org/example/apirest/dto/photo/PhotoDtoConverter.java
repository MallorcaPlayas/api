package org.example.apirest.dto.photo;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.model.Photo;
import org.example.apirest.service.s3.S3Service;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PhotoDtoConverter implements DtoConverter<Photo, PhotoDto> {

    private final ModelMapper mapper;
    private final S3Service s3Service;

    @Override
    public PhotoDto entityToDto(Photo photo) {
        if(photo == null) return null;
        PhotoDto photoDto = mapper.map(photo, PhotoDto.class);
        if (photo.isPrivate()) {
            String url = this.s3Service.generateTeamporalUrl(photo.getBucket(), photo.getPath());
            photoDto.setUrl(url);
        }
        return photoDto;
    }

    @Override
    public List<PhotoDto> entityListToDtoList(List<Photo> photos) {
        return photos.stream().map(this::entityToDto).toList();
    }

    @Override
    public Photo dtoToEntity(PhotoDto photoDto) {
        return mapper.map(photoDto , Photo.class);
    }

    @Override
    public List<Photo> dtoListToEntityList(List<PhotoDto> photoDtos) {
        return photoDtos.stream().map(this::dtoToEntity).toList();
    }

    //    @Override
//    public PhotoDto convertDto(Photo entity, Class<PhotoDto> dtoClass) {
//        PhotoDto photoDto = modelMapper.map(entity, dtoClass);
//        return photoDto;
//    }
//
//    @Override
//    public List<PhotoDto> convertDtoList(List<Photo> entities, Class<PhotoDto> dtoClass) {
//        return entities.stream().map(entity -> convertDto(entity, dtoClass)).toList();
//    }
//
//    @Override
//    public Photo convertToEntityFromDto(PhotoDto dto, Class<Photo> entityClass) {
//        return modelMapper.map(dto, entityClass);
//    }
//
//    @Override
//    public Photo convertToEntityFromCreateDto(CreatePhotoDto createDto, Class<Photo> entityClass) {
//        Photo entity = modelMapper.map(createDto, entityClass);
//        if (entity.getIsPrivate() == null) {
//            entity.setIsPrivate(false);  // Valor por defecto
//        }
//        entity.setId(null);
//        return entity;
//    }
//
//    @Override
//    public List<Photo> convertToEntityListFromCreateDto(List<CreatePhotoDto> createDtos, Class<Photo> entityClass) {
//        return createDtos.stream().map(createDto -> convertToEntityFromCreateDto(createDto, entityClass)).toList();
//    }
//
//    @Override
//    public List<Photo> convertToEntityListFromDto(List<PhotoDto> dtos, Class<Photo> entityClass) {
//        return dtos.stream().map(createDto -> convertToEntityFromDto(createDto, entityClass)).toList();
//    }
}
