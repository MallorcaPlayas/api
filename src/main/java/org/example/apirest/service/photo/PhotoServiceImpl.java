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
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl extends GeneralizedServiceImpl<Photo, PhotoDto, CreatePhotoDto, PhotoRepository> {
    public PhotoServiceImpl(PhotoRepository repository, DtoConverterImpl<Photo,PhotoDto,CreatePhotoDto> dtoConverter) {
        super(repository, dtoConverter, Photo.class, PhotoDto.class);
    }
}
