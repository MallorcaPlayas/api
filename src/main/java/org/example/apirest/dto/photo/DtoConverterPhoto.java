package org.example.apirest.dto.photo;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.model.BaseEntity;
import org.example.apirest.model.Photo;
import org.example.apirest.service.s3.S3Service;
import org.modelmapper.ModelMapper;
import org.neo4j.cypherdsl.core.Create;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DtoConverterPhoto implements DtoConverter<Photo, PhotoDto, CreatePhotoDto> {

    private final ModelMapper modelMapper;
    private final S3Service s3Service;

    @Override
    public PhotoDto convertDto(Photo entity, Class<PhotoDto> dtoClass) {
        PhotoDto photoDto = modelMapper.map(entity, dtoClass);
        photoDto.setUrl(s3Service.urlGenerator(entity.getBucket(),entity.getPath()));
        return photoDto;
    }

    @Override
    public List<PhotoDto> convertDtoList(List<Photo> entities, Class<PhotoDto> dtoClass) {
        return entities.stream().map(entity -> convertDto(entity, dtoClass)).toList();
    }

    @Override
    public Photo convertToEntityFromDto(PhotoDto dto, Class<Photo> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    @Override
    public Photo convertToEntityFromCreateDto(CreatePhotoDto createDto, Class<Photo> entityClass) {
        Photo entity = modelMapper.map(createDto, entityClass);
        entity.setId(null);
        return entity;
    }

    @Override
    public List<Photo> convertToEntityListFromCreateDto(List<CreatePhotoDto> createDtos, Class<Photo> entityClass) {
        return createDtos.stream().map(createDto -> convertToEntityFromCreateDto(createDto, entityClass)).toList();
    }

    @Override
    public List<Photo> convertToEntityListFromDto(List<PhotoDto> dtos, Class<Photo> entityClass) {
        return dtos.stream().map(createDto -> convertToEntityFromDto(createDto, entityClass)).toList();
    }
}
