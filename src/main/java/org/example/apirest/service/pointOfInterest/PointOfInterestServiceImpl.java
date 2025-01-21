package org.example.apirest.service.pointOfInterest;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.pointOfInterest.PointOfInterestDto;
import org.example.apirest.dto.pointOfInterest.CreatePointOfInterestDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Photo;
import org.example.apirest.model.PointOfInterest;
import org.example.apirest.repository.PhotoRepository;
import org.example.apirest.repository.PointOfInterestRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointOfInterestServiceImpl extends GeneralizedServiceImpl<PointOfInterest, PointOfInterestDto, CreatePointOfInterestDto, PointOfInterestRepository> {
    public PointOfInterestServiceImpl(PointOfInterestRepository repository, DtoConverterImpl<PointOfInterest,PointOfInterestDto,CreatePointOfInterestDto> dtoConverter) {
        super(repository, dtoConverter, PointOfInterest.class, PointOfInterestDto.class);
    }
}
