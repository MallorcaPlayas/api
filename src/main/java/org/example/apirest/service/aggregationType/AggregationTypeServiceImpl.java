package org.example.apirest.service.aggregationType;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.aggregationType.AggregationTypeDto;
import org.example.apirest.dto.aggregationType.CreateAggregationTypeDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.AggregationType;
import org.example.apirest.repository.AggregationTypeRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AggregationTypeServiceImpl implements AggregationTypeService {

    private final AggregationTypeRepository repository;
    private final DtoConverterImpl<AggregationType, AggregationTypeDto, CreateAggregationTypeDto> dtoConverter;

    @Override
    public List<AggregationTypeDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), AggregationTypeDto.class);
    }

    @Override
    public AggregationTypeDto findOne(Long id) {
        AggregationType aggregationType = repository.findById(id).orElseThrow(() -> new NotFoundException(AggregationType.class, id));
        return dtoConverter.convertDto(aggregationType, AggregationTypeDto.class);
    }

    @Override
    public AggregationTypeDto save(CreateAggregationTypeDto aggregationType) {
        AggregationType aggregationTypeToInsert = dtoConverter.convertToEntityFromCreateDto(aggregationType, AggregationType.class);
        return dtoConverter.convertDto(repository.save(aggregationTypeToInsert), AggregationTypeDto.class);
    }

    @Override
    public AggregationTypeDto update(Long id, CreateAggregationTypeDto aggregationType) {
        AggregationType oldAggregationType = repository.findById(id).orElseThrow(() -> new NotFoundException(AggregationType.class, id));
        AggregationType aggregationTypeToInsert = dtoConverter.convertToEntityFromCreateDto(aggregationType, AggregationType.class);

        if (oldAggregationType == null) {
            return null;
        }

        UtilsClass.updateFields(oldAggregationType, aggregationTypeToInsert);

        return dtoConverter.convertDto(repository.save(oldAggregationType), AggregationTypeDto.class);
    }

    @Override
    public void delete(Long id) {
        AggregationType aggregationType = repository.findById(id).orElseThrow(() -> new NotFoundException(AggregationType.class, id));
        repository.delete(aggregationType);
    }
}
