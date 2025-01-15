package org.example.apirest.service.beach;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Beach;
import org.example.apirest.repository.BeachRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeachServiceImpl implements BeachService {

    private final BeachRepository repository;
    private final DtoConverterImpl<Beach,BeachDto,CreateBeachDto> dtoConverter;

    @Override
    public List<BeachDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(),BeachDto.class);
    }

    @Override
    public BeachDto findOne(Long id) {
        Beach beach = repository.findById(id).orElseThrow(()-> new NotFoundException(Beach.class,id));
        return dtoConverter.convertDto(beach,BeachDto.class);
    }

    @Override
    public BeachDto save(CreateBeachDto beach) {
        Beach beachToInsert = dtoConverter.convertToEntityFromCreateDto(beach,Beach.class);
        return dtoConverter.convertDto(repository.save(beachToInsert),BeachDto.class);
    }

    @Override
    public BeachDto update(Long id, CreateBeachDto beach) {
        Beach oldBeach = repository.findById(id).orElseThrow(()-> new NotFoundException(Beach.class,id));
        Beach beachToInsert = dtoConverter.convertToEntityFromCreateDto(beach,Beach.class);

        if (oldBeach == null) {
            return null;
        }

        UtilsClass.updateFields(oldBeach, beachToInsert);

        return dtoConverter.convertDto(repository.save(oldBeach),BeachDto.class);
    }

    @Override
    public void delete(Long id) {
        Beach beach = repository.findById(id).orElseThrow(()-> new NotFoundException(Beach.class,id));

        repository.delete(beach);
    }
}
