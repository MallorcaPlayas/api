package org.example.apirest.service.beach;

import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.model.Beach;

import java.util.List;

public interface BeachService {
    List<BeachDto> findAll();
    BeachDto findOne(Long id);
    List<Beach> findAllNewest(int openSince);
    BeachDto save(CreateBeachDto restaurant);
    BeachDto update(Long id, CreateBeachDto restaurant);
    void delete(Long id);
}
