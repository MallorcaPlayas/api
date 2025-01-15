package org.example.apirest.service.pointOfInterestType;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.pointOfInterestType.PointOfInterestTypeDto;
import org.example.apirest.dto.pointOfInterestType.CreatePointOfInterestTypeDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.PointOfInterestType;
import org.example.apirest.repository.PointOfInterestTypeRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointOfInterestTypeServiceImpl implements PointOfInterestTypeService {

    private final PointOfInterestTypeRepository repository;
    private final DtoConverterImpl<PointOfInterestType, PointOfInterestTypeDto, CreatePointOfInterestTypeDto> dtoConverter;

    @Override
    public List<PointOfInterestTypeDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), PointOfInterestTypeDto.class);
    }

    @Override
    public PointOfInterestTypeDto findOne(Long id) {
        PointOfInterestType pointOfInterestType = repository.findById(id).orElseThrow(() -> new NotFoundException(PointOfInterestType.class, id));
        return dtoConverter.convertDto(pointOfInterestType, PointOfInterestTypeDto.class);
    }

    @Override
    public PointOfInterestTypeDto save(CreatePointOfInterestTypeDto pointOfInterestType) {
        PointOfInterestType pointOfInterestTypeToInsert = dtoConverter.convertToEntityFromCreateDto(pointOfInterestType, PointOfInterestType.class);
        return dtoConverter.convertDto(repository.save(pointOfInterestTypeToInsert), PointOfInterestTypeDto.class);
    }

    @Override
    public PointOfInterestTypeDto update(Long id, CreatePointOfInterestTypeDto pointOfInterestType) {
        PointOfInterestType oldPointOfInterestType = repository.findById(id).orElseThrow(() -> new NotFoundException(PointOfInterestType.class, id));
        PointOfInterestType pointOfInterestTypeToInsert = dtoConverter.convertToEntityFromCreateDto(pointOfInterestType, PointOfInterestType.class);

        if (oldPointOfInterestType == null) {
            return null;
        }

        UtilsClass.updateFields(oldPointOfInterestType, pointOfInterestTypeToInsert);

        return dtoConverter.convertDto(repository.save(oldPointOfInterestType), PointOfInterestTypeDto.class);
    }

    @Override
    public void delete(Long id) {
        PointOfInterestType pointOfInterestType = repository.findById(id).orElseThrow(() -> new NotFoundException(PointOfInterestType.class, id));
        repository.delete(pointOfInterestType);
    }
}
