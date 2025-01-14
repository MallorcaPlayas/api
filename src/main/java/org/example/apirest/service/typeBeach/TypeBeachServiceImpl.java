package org.example.apirest.service.typeBeach;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.model.TypeBeach;
import org.example.apirest.repository.TypeBeachRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeBeachServiceImpl implements TypeBeachService {


    private final TypeBeachRepository serviceRepository;
    private final DtoConverterImpl<TypeBeach, TypeBeachDto, CreateTypeBeachDto> serviceDtoConverter;

    @Override
    public List<TypeBeachDto> findAll() {
        return serviceDtoConverter.convertDtoList(serviceRepository.findAll());
    }

    @Override
    public TypeBeachDto findOne(Long id) {
        TypeBeach typeBeach = serviceRepository.findById(id).orElse(null);
        return serviceDtoConverter.convertDto(typeBeach);
    }

    @Override
    public TypeBeachDto save(CreateTypeBeachDto typeBeach) {
        TypeBeach typeBeachToInsert = serviceDtoConverter.convertToEntityFromCreateDto(typeBeach);
        return serviceDtoConverter.convertDto(serviceRepository.save(typeBeachToInsert));
    }

    @Override
    public TypeBeachDto update(Long id, CreateTypeBeachDto typeBeach) {
        TypeBeach oldTypeBeach = serviceRepository.findById(id).orElse(null);
        TypeBeach typeBeachToInsert = serviceDtoConverter.convertToEntityFromCreateDto(typeBeach);

        if (oldTypeBeach == null) {
            return null;
        }

        oldTypeBeach.setName(typeBeachToInsert.getName());
        oldTypeBeach.setBeaches(typeBeachToInsert.getBeaches());

        return serviceDtoConverter.convertDto(serviceRepository.save(oldTypeBeach));
    }

    @Override
    public void delete(Long id) {
        TypeBeach typeBeach = serviceRepository.findById(id).orElse(null);

        serviceRepository.delete(typeBeach);
    }
}
