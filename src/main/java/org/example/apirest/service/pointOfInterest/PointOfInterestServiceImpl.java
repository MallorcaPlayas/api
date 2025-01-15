package org.example.apirest.service.pointOfInterest;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.pointOfInterest.PointOfInterestDto;
import org.example.apirest.dto.pointOfInterest.CreatePointOfInterestDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.PointOfInterest;
import org.example.apirest.repository.PointOfInterestRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointOfInterestServiceImpl implements PointOfInterestService {

    private final PointOfInterestRepository repository;
    private final DtoConverterImpl<PointOfInterest, PointOfInterestDto, CreatePointOfInterestDto> dtoConverter;

    @Override
    public List<PointOfInterestDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), PointOfInterestDto.class);
    }

    @Override
    public PointOfInterestDto findOne(Long id) {
        PointOfInterest pointOfInterest = repository.findById(id).orElseThrow(() -> new NotFoundException(PointOfInterest.class, id));
        return dtoConverter.convertDto(pointOfInterest, PointOfInterestDto.class);
    }

    @Override
    public PointOfInterestDto save(CreatePointOfInterestDto pointOfInterest) {
        PointOfInterest pointOfInterestToInsert = dtoConverter.convertToEntityFromCreateDto(pointOfInterest, PointOfInterest.class);
        return dtoConverter.convertDto(repository.save(pointOfInterestToInsert), PointOfInterestDto.class);
    }

    @Override
    public PointOfInterestDto update(Long id, CreatePointOfInterestDto pointOfInterest) {
        PointOfInterest oldPointOfInterest = repository.findById(id).orElseThrow(() -> new NotFoundException(PointOfInterest.class, id));
        PointOfInterest pointOfInterestToInsert = dtoConverter.convertToEntityFromCreateDto(pointOfInterest, PointOfInterest.class);

        if (oldPointOfInterest == null) {
            return null;
        }

        UtilsClass.updateFields(oldPointOfInterest, pointOfInterestToInsert);

        return dtoConverter.convertDto(repository.save(oldPointOfInterest), PointOfInterestDto.class);
    }

    @Override
    public void delete(Long id) {
        PointOfInterest pointOfInterest = repository.findById(id).orElseThrow(() -> new NotFoundException(PointOfInterest.class, id));
        repository.delete(pointOfInterest);
    }
}
