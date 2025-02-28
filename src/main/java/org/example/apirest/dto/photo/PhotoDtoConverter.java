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
    public Photo dtoToEntity(PhotoDto photoDto) {
        return mapper.map(photoDto , Photo.class);
    }

}
