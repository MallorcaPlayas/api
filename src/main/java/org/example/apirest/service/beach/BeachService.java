package org.example.apirest.service.beach;

import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;

import java.util.List;

public interface BeachService {
    List<BeachDto> findAll();
    BeachDto findOne(Long id);
    BeachDto save(CreateBeachDto beach);
    BeachDto update(Long id, CreateBeachDto beach);
    void delete(Long id);
}
