package org.example.apirest.service.roadType;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.roadType.CreateRoadTypeDto;
import org.example.apirest.dto.roadType.RoadTypeDto;
import org.example.apirest.model.RoadType;
import org.example.apirest.repository.RoadTypeRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoadTypeServiceImpl extends GeneralizedServiceImpl<RoadType, RoadTypeDto, CreateRoadTypeDto, RoadTypeRepository> {
    public RoadTypeServiceImpl(RoadTypeRepository repository, DtoConverterGeneralizedImpl<RoadType,RoadTypeDto,CreateRoadTypeDto> dtoConverter) {
        super(repository, dtoConverter, RoadType.class, RoadTypeDto.class);
    }
}
