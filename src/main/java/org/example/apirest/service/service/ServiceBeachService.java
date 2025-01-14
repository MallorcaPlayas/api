package org.example.apirest.service.service;


import org.example.apirest.dto.service.CreateServiceBeachDto;
import org.example.apirest.dto.service.ServiceBeachDto;

import java.util.List;

public interface ServiceBeachService {
    List<ServiceBeachDto> findAll();
    ServiceBeachDto findOne(Long id);
    ServiceBeachDto save(CreateServiceBeachDto service);
    ServiceBeachDto update(Long id, CreateServiceBeachDto service);
    void delete(Long id);
}
