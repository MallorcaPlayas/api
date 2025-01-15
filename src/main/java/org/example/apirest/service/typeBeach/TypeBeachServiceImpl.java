package org.example.apirest.service.typeBeach;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.service.ServiceBeachDto;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.ServiceBeach;
import org.example.apirest.model.TypeBeach;
import org.example.apirest.repository.TypeBeachRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeBeachServiceImpl implements TypeBeachService {


    private final TypeBeachRepository serviceRepository;
    private final DtoConverterImpl<TypeBeach, TypeBeachDto, CreateTypeBeachDto> serviceDtoConverter;

    @Override
    public List<TypeBeachDto> findAll() {
        return serviceDtoConverter.convertDtoList(serviceRepository.findAll(), TypeBeachDto.class);
    }

    @Override
    public TypeBeachDto findOne(Long id) {
        TypeBeach typeBeach = serviceRepository.findById(id).orElseThrow(()-> new NotFoundException(TypeBeach.class,id));
        return serviceDtoConverter.convertDto(typeBeach, TypeBeachDto.class);
    }

    @Override
    public TypeBeachDto save(CreateTypeBeachDto typeBeach) {
        TypeBeach typeBeachToInsert = serviceDtoConverter.convertToEntityFromCreateDto(typeBeach, TypeBeach.class);
        return serviceDtoConverter.convertDto(serviceRepository.save(typeBeachToInsert), TypeBeachDto.class);
    }

    @Override
    public TypeBeachDto update(Long id, CreateTypeBeachDto typeBeach) {
        TypeBeach oldTypeBeach = serviceRepository.findById(id).orElseThrow(()-> new NotFoundException(TypeBeach.class,id));
        TypeBeach typeBeachToInsert = serviceDtoConverter.convertToEntityFromCreateDto(typeBeach, TypeBeach.class);

        if (oldTypeBeach == null) {
            return null;
        }

        UtilsClass.updateFields(oldTypeBeach, typeBeachToInsert);

        return serviceDtoConverter.convertDto(serviceRepository.save(oldTypeBeach), TypeBeachDto.class);
    }

    @Override
    public void delete(Long id) {
        TypeBeach typeBeach = serviceRepository.findById(id).orElseThrow(()-> new NotFoundException(TypeBeach.class,id));

        serviceRepository.delete(typeBeach);
    }
}
