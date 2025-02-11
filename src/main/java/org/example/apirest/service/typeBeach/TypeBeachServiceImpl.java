package org.example.apirest.service.typeBeach;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.TypeBeach;
import org.example.apirest.repository.TypeBeachRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeBeachServiceImpl implements DtoConverter<TypeBeach, TypeBeachDto, CreateTypeBeachDto> {

    private final TypeBeachRepository repository;
    private final ModelMapper mapper;

    public List<TypeBeachDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public TypeBeachDto findOne(Long id) {
        TypeBeach typeBeach = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(TypeBeach.class, id));
        return this.toDto(typeBeach);
    }

    public TypeBeachDto save(CreateTypeBeachDto createTypeBeachDto) {
        TypeBeach typeBeach = fromDto(createTypeBeachDto);
        TypeBeach savedTypeBeach = repository.save(typeBeach);
        return toDto(savedTypeBeach);
    }

    public TypeBeachDto update(Long id, CreateTypeBeachDto createTypeBeachDto) {
        TypeBeach oldTypeBeach = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(TypeBeach.class, id));
        TypeBeach typeBeachToUpdate = fromDto(createTypeBeachDto);

        UtilsClass.updateFields(oldTypeBeach, typeBeachToUpdate);

        TypeBeach savedTypeBeach = repository.save(oldTypeBeach);
        return toDto(savedTypeBeach);
    }

    public void delete(Long id) {
        TypeBeach typeBeach = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(TypeBeach.class, id));
        repository.delete(typeBeach);
    }

    @Override
    public TypeBeachDto toDto(TypeBeach typeBeach) {
        return mapper.map(typeBeach, TypeBeachDto.class);
    }

    @Override
    public List<TypeBeachDto> toDtoList(List<TypeBeach> typeBeaches) {
        return typeBeaches.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public TypeBeach fromDto(CreateTypeBeachDto createTypeBeachDto) {
        return mapper.map(createTypeBeachDto, TypeBeach.class);
    }

    @Override
    public List<TypeBeach> fromDtoList(List<CreateTypeBeachDto> createTypeBeachDtos) {
        return createTypeBeachDtos.stream()
                .map(this::fromDto)
                .toList();
    }
}
