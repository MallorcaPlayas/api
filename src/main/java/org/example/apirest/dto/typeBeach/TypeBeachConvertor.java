package org.example.apirest.dto.typeBeach;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.model.TypeBeach;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TypeBeachConvertor implements DtoConverter<TypeBeach,TypeBeachDto,CreateTypeBeachDto> {
    private final ModelMapper modelMapper;

    public TypeBeachDto convertDto(TypeBeach typeBeach) {
        return modelMapper.map(typeBeach, TypeBeachDto.class);
    }

    public List<TypeBeachDto> convertDtoList(List<TypeBeach> typeBeachList) {
        return typeBeachList.stream().map(this::convertDto).toList();
    }

    public TypeBeach convertToEntityFromDto(TypeBeachDto typeBeachDto) {
        return modelMapper.map(typeBeachDto, TypeBeach.class);
    }

    public TypeBeach convertToEntityFromCreateDto(CreateTypeBeachDto createTypeBeachDto) {
        TypeBeach typeBeach = modelMapper.map(createTypeBeachDto, TypeBeach.class);
        typeBeach.setId(null);
        return typeBeach;
    }

    public List<TypeBeach> convertToEntityListFromCreateDto(List<CreateTypeBeachDto> typeBeachDtos) {
        return typeBeachDtos.stream().map(this::convertToEntityFromCreateDto).toList();
    }
}