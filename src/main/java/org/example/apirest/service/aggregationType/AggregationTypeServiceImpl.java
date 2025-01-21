package org.example.apirest.service.aggregationType;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.aggregationType.AggregationTypeDto;
import org.example.apirest.dto.aggregationType.CreateAggregationTypeDto;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.AggregationType;
import org.example.apirest.model.Beach;
import org.example.apirest.repository.AggregationTypeRepository;
import org.example.apirest.repository.BeachRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AggregationTypeServiceImpl extends GeneralizedServiceImpl<AggregationType, AggregationTypeDto, CreateAggregationTypeDto, AggregationTypeRepository> {
    public AggregationTypeServiceImpl(AggregationTypeRepository repository, DtoConverterImpl<AggregationType,AggregationTypeDto,CreateAggregationTypeDto> dtoConverter) {
        super(repository, dtoConverter, AggregationType.class, AggregationTypeDto.class);
    }
}
