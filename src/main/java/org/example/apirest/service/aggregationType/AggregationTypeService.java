package org.example.apirest.service.aggregationType;

import org.example.apirest.dto.aggregationType.AggregationTypeDto;
import org.example.apirest.dto.aggregationType.CreateAggregationTypeDto;

import java.util.List;

public interface AggregationTypeService {
    List<AggregationTypeDto> findAll();
    AggregationTypeDto findOne(Long id);
    AggregationTypeDto save(CreateAggregationTypeDto aggregationType);
    AggregationTypeDto update(Long id, CreateAggregationTypeDto aggregationType);
    void delete(Long id);
}
