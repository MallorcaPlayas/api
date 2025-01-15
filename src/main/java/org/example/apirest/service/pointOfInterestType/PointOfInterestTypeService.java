package org.example.apirest.service.pointOfInterestType;

import org.example.apirest.dto.pointOfInterestType.PointOfInterestTypeDto;
import org.example.apirest.dto.pointOfInterestType.CreatePointOfInterestTypeDto;

import java.util.List;

public interface PointOfInterestTypeService {
    List<PointOfInterestTypeDto> findAll();
    PointOfInterestTypeDto findOne(Long id);
    PointOfInterestTypeDto save(CreatePointOfInterestTypeDto pointOfInterestType);
    PointOfInterestTypeDto update(Long id, CreatePointOfInterestTypeDto pointOfInterestType);
    void delete(Long id);
}
