package org.example.apirest.service.pointOfInterestType;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.pointOfInterestType.PointOfInterestTypeDto;
import org.example.apirest.dto.pointOfInterestType.CreatePointOfInterestTypeDto;
import org.example.apirest.model.PointOfInterestType;
import org.example.apirest.repository.PointOfInterestTypeRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PointOfInterestTypeServiceImpl extends GeneralizedServiceImpl<PointOfInterestType, PointOfInterestTypeDto, CreatePointOfInterestTypeDto, PointOfInterestTypeRepository> {
    public PointOfInterestTypeServiceImpl(PointOfInterestTypeRepository repository, DtoConverterGeneralizedImpl<PointOfInterestType,PointOfInterestTypeDto,CreatePointOfInterestTypeDto> dtoConverter) {
        super(repository, dtoConverter, PointOfInterestType.class, PointOfInterestTypeDto.class);
    }
}
