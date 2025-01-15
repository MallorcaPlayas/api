package org.example.apirest.service.excursion;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.excursion.ExcursionDto;
import org.example.apirest.dto.excursion.CreateExcursionDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Excursion;
import org.example.apirest.repository.ExcursionRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcursionServiceImpl implements ExcursionService {

    private final ExcursionRepository repository;
    private final DtoConverterImpl<Excursion, ExcursionDto, CreateExcursionDto> dtoConverter;

    @Override
    public List<ExcursionDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), ExcursionDto.class);
    }

    @Override
    public ExcursionDto findOne(Long id) {
        Excursion excursion = repository.findById(id).orElseThrow(() -> new NotFoundException(Excursion.class, id));
        return dtoConverter.convertDto(excursion, ExcursionDto.class);
    }

    @Override
    public ExcursionDto save(CreateExcursionDto excursion) {
        Excursion excursionToInsert = dtoConverter.convertToEntityFromCreateDto(excursion, Excursion.class);
        return dtoConverter.convertDto(repository.save(excursionToInsert), ExcursionDto.class);
    }

    @Override
    public ExcursionDto update(Long id, CreateExcursionDto excursion) {
        Excursion oldExcursion = repository.findById(id).orElseThrow(() -> new NotFoundException(Excursion.class, id));
        Excursion excursionToInsert = dtoConverter.convertToEntityFromCreateDto(excursion, Excursion.class);

        if (oldExcursion == null) {
            return null;
        }

        UtilsClass.updateFields(oldExcursion, excursionToInsert);

        return dtoConverter.convertDto(repository.save(oldExcursion), ExcursionDto.class);
    }

    @Override
    public void delete(Long id) {
        Excursion excursion = repository.findById(id).orElseThrow(() -> new NotFoundException(Excursion.class, id));
        repository.delete(excursion);
    }
}
