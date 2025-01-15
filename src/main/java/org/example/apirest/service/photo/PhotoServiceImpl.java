package org.example.apirest.service.photo;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Photo;
import org.example.apirest.repository.PhotoRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository repository;
    private final DtoConverterImpl<Photo, PhotoDto, CreatePhotoDto> dtoConverter;

    @Override
    public List<PhotoDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), PhotoDto.class);
    }

    @Override
    public PhotoDto findOne(Long id) {
        Photo photo = repository.findById(id).orElseThrow(() -> new NotFoundException(Photo.class, id));
        return dtoConverter.convertDto(photo, PhotoDto.class);
    }

    @Override
    public PhotoDto save(CreatePhotoDto photo) {
        Photo photoToInsert = dtoConverter.convertToEntityFromCreateDto(photo, Photo.class);
        return dtoConverter.convertDto(repository.save(photoToInsert), PhotoDto.class);
    }

    @Override
    public PhotoDto update(Long id, CreatePhotoDto photo) {
        Photo oldPhoto = repository.findById(id).orElseThrow(() -> new NotFoundException(Photo.class, id));
        Photo photoToInsert = dtoConverter.convertToEntityFromCreateDto(photo, Photo.class);

        if (oldPhoto == null) {
            return null;
        }

        UtilsClass.updateFields(oldPhoto, photoToInsert);

        return dtoConverter.convertDto(repository.save(oldPhoto), PhotoDto.class);
    }

    @Override
    public void delete(Long id) {
        Photo photo = repository.findById(id).orElseThrow(() -> new NotFoundException(Photo.class, id));
        repository.delete(photo);
    }
}
