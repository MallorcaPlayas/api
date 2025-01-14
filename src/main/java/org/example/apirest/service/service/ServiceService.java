package org.example.apirest.service.service;


import org.example.apirest.dto.service.CreateServiceDto;
import org.example.apirest.dto.service.ServiceDto;

import java.util.List;

public interface ServiceService {
    List<ServiceDto> findAll();
    ServiceDto findOne(Long id);
    ServiceDto save(CreateServiceDto service);
    ServiceDto update(Long id, CreateServiceDto service);
    void delete(Long id);
}
