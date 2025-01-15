package org.example.apirest.service.pointOfInterest;

import org.example.apirest.dto.pointOfInterest.PointOfInterestDto;
import org.example.apirest.dto.pointOfInterest.CreatePointOfInterestDto;

import java.util.List;

public interface PointOfInterestService {
    List<PointOfInterestDto> findAll();
    PointOfInterestDto findOne(Long id);
    PointOfInterestDto save(CreatePointOfInterestDto pointOfInterest);
    PointOfInterestDto update(Long id, CreatePointOfInterestDto pointOfInterest);
    void delete(Long id);
}
