package org.example.apirest.service.horary;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.horary.CreateHoraryDto;
import org.example.apirest.dto.horary.HoraryDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Horary;
import org.example.apirest.repository.HoraryRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HoraryServiceImpl implements DtoConverter<Horary, HoraryDto, CreateHoraryDto> {

    private final HoraryRepository repository;
    private final ModelMapper mapper;

    public List<HoraryDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public HoraryDto findOne(Long id) {
        Horary entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Horary.class, id));
        return this.toDto(entity);
    }

    public HoraryDto save(CreateHoraryDto dto) {
        Horary entityToInsert = fromDto(dto);
        return toDto(repository.save(entityToInsert));
    }

    public HoraryDto update(Long id, CreateHoraryDto dto) {
        Horary oldEntity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Horary.class, id));
        Horary newEntity = fromDto(dto);

        UtilsClass.updateFields(oldEntity, newEntity);
        return toDto(repository.save(oldEntity));
    }

    public void delete(Long id) {
        Horary entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Horary.class, id));
        repository.delete(entity);
    }

    @Override
    public HoraryDto toDto(Horary entity) {
        return mapper.map(entity, HoraryDto.class);
    }

    @Override
    public List<HoraryDto> toDtoList(List<Horary> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public Horary fromDto(CreateHoraryDto dto) {
        return mapper.map(dto, Horary.class);
    }

    @Override
    public List<Horary> fromDtoList(List<CreateHoraryDto> dtos) {
        return dtos.stream()
                .map(this::fromDto)
                .toList();
    }
}
