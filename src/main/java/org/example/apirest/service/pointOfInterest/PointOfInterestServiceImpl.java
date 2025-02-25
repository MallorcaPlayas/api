package org.example.apirest.service.pointOfInterest;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.pointOfInterest.PointOfInterestDto;
import org.example.apirest.dto.pointOfInterest.CreatePointOfInterestDto;
import org.example.apirest.model.PointOfInterest;
import org.example.apirest.repository.PointOfInterestRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PointOfInterestServiceImpl extends GeneralizedServiceImpl<PointOfInterest, PointOfInterestDto, CreatePointOfInterestDto, PointOfInterestRepository> {
    public PointOfInterestServiceImpl(PointOfInterestRepository repository, DtoConverterGeneralizedImpl<PointOfInterest,PointOfInterestDto,CreatePointOfInterestDto> dtoConverter) {
        super(repository, dtoConverter, PointOfInterest.class, PointOfInterestDto.class);
    }
}
