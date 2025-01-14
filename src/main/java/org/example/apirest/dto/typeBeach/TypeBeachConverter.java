package org.example.apirest.dto.typeBeach;

import lombok.RequiredArgsConstructor;
import org.example.apirest.model.TypeBeach;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TypeBeachConverter {
    private final ModelMapper modelMapper;

    public TypeBeachDto convertDto(TypeBeach typeBeach) {
        return modelMapper.map(typeBeach, TypeBeachDto.class);
    }

    public List<TypeBeachDto> convertDtoList(List<TypeBeach> serviceList) {
        return serviceList.stream().map(this::convertDto).toList();
    }

    public TypeBeach convertToCreateServiceEntity(TypeBeachDto serviceDto) {
        return modelMapper.map(serviceDto, TypeBeach.class);
    }

    public TypeBeach convertToCreateServiceEntity(CreateTypeBeachDto createTypeBeachDto) {
        TypeBeach typeBeach = modelMapper.map(createTypeBeachDto, TypeBeach.class);
        typeBeach.setId(null);
        return typeBeach;
    }

    public List<TypeBeach> convertToEntityList(List<CreateTypeBeachDto> typeBeachDtos) {
        return typeBeachDtos.stream().map(this::convertToCreateServiceEntity).toList();
    }
}
