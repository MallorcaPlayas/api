package org.example.apirest.service.horary;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.horary.HoraryDto;
import org.example.apirest.dto.horary.CreateHoraryDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Horary;
import org.example.apirest.repository.HoraryRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HoraryServiceImpl implements HoraryService {

    private final HoraryRepository repository;
    private final DtoConverterImpl<Horary, HoraryDto, CreateHoraryDto> dtoConverter;

    @Override
    public List<HoraryDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), HoraryDto.class);
    }

    @Override
    public HoraryDto findOne(Long id) {
        Horary horary = repository.findById(id).orElseThrow(() -> new NotFoundException(Horary.class, id));
        return dtoConverter.convertDto(horary, HoraryDto.class);
    }

    @Override
    public HoraryDto save(CreateHoraryDto horary) {
        Horary horaryToInsert = dtoConverter.convertToEntityFromCreateDto(horary, Horary.class);
        return dtoConverter.convertDto(repository.save(horaryToInsert), HoraryDto.class);
    }

    @Override
    public HoraryDto update(Long id, CreateHoraryDto horary) {
        Horary oldHorary = repository.findById(id).orElseThrow(() -> new NotFoundException(Horary.class, id));
        Horary horaryToInsert = dtoConverter.convertToEntityFromCreateDto(horary, Horary.class);

        if (oldHorary == null) {
            return null;
        }

        UtilsClass.updateFields(oldHorary, horaryToInsert);

        return dtoConverter.convertDto(repository.save(oldHorary), HoraryDto.class);
    }

    @Override
    public void delete(Long id) {
        Horary horary = repository.findById(id).orElseThrow(() -> new NotFoundException(Horary.class, id));
        repository.delete(horary);
    }
}
