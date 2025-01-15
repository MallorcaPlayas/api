package org.example.apirest.service.excursion;

import org.example.apirest.dto.excursion.ExcursionDto;
import org.example.apirest.dto.excursion.CreateExcursionDto;

import java.util.List;

public interface ExcursionService {
    List<ExcursionDto> findAll();
    ExcursionDto findOne(Long id);
    ExcursionDto save(CreateExcursionDto excursion);
    ExcursionDto update(Long id, CreateExcursionDto excursion);
    void delete(Long id);
}
