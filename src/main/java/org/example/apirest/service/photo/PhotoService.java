package org.example.apirest.service.photo;

import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.photo.CreatePhotoDto;

import java.util.List;

public interface PhotoService {
    List<PhotoDto> findAll();
    PhotoDto findOne(Long id);
    PhotoDto save(CreatePhotoDto photo);
    PhotoDto update(Long id, CreatePhotoDto photo);
    void delete(Long id);
}
