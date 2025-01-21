package org.example.apirest.service.pointOfInterestType;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.pointOfInterest.CreatePointOfInterestDto;
import org.example.apirest.dto.pointOfInterest.PointOfInterestDto;
import org.example.apirest.dto.pointOfInterestType.PointOfInterestTypeDto;
import org.example.apirest.dto.pointOfInterestType.CreatePointOfInterestTypeDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.PointOfInterest;
import org.example.apirest.model.PointOfInterestType;
import org.example.apirest.repository.PointOfInterestRepository;
import org.example.apirest.repository.PointOfInterestTypeRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointOfInterestTypeServiceImpl extends GeneralizedServiceImpl<PointOfInterestType, PointOfInterestTypeDto, CreatePointOfInterestTypeDto, PointOfInterestTypeRepository> {
    public PointOfInterestTypeServiceImpl(PointOfInterestTypeRepository repository, DtoConverterImpl<PointOfInterestType,PointOfInterestTypeDto,CreatePointOfInterestTypeDto> dtoConverter) {
        super(repository, dtoConverter, PointOfInterestType.class, PointOfInterestTypeDto.class);
    }
}
