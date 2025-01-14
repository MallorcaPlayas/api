package org.example.apirest.service.typeBeach;

import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;

import java.util.List;

public interface TypeBeach {
    List<TypeBeachDto> findAll();
    TypeBeachDto findOne(Long id);
    TypeBeachDto save(CreateTypeBeachDto restaurant);
    TypeBeachDto update(Long id, CreateTypeBeachDto restaurant);
    void delete(Long id);
}
