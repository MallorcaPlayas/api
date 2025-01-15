package org.example.apirest.service.typeBeach;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.TypeBeach;
import org.example.apirest.repository.TypeBeachRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeBeachServiceImpl implements TypeBeachService {


    private final TypeBeachRepository repository;
    private final DtoConverterImpl<TypeBeach, TypeBeachDto, CreateTypeBeachDto> dtoConverter;

    @Override
    public List<TypeBeachDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), TypeBeachDto.class);
    }

    @Override
    public TypeBeachDto findOne(Long id) {
        TypeBeach typeBeach = repository.findById(id).orElseThrow(()-> new NotFoundException(TypeBeach.class,id));
        return dtoConverter.convertDto(typeBeach, TypeBeachDto.class);
    }

    @Override
    public TypeBeachDto save(CreateTypeBeachDto typeBeach) {
        TypeBeach typeBeachToInsert = dtoConverter.convertToEntityFromCreateDto(typeBeach, TypeBeach.class);
        return dtoConverter.convertDto(repository.save(typeBeachToInsert), TypeBeachDto.class);
    }

    @Override
    public TypeBeachDto update(Long id, CreateTypeBeachDto typeBeach) {
        TypeBeach oldTypeBeach = repository.findById(id).orElseThrow(()-> new NotFoundException(TypeBeach.class,id));
        TypeBeach typeBeachToInsert = dtoConverter.convertToEntityFromCreateDto(typeBeach, TypeBeach.class);

        if (oldTypeBeach == null) {
            return null;
        }

        UtilsClass.updateFields(oldTypeBeach, typeBeachToInsert);

        return dtoConverter.convertDto(repository.save(oldTypeBeach), TypeBeachDto.class);
    }

    @Override
    public void delete(Long id) {
        TypeBeach typeBeach = repository.findById(id).orElseThrow(()-> new NotFoundException(TypeBeach.class,id));

        repository.delete(typeBeach);
    }
}
