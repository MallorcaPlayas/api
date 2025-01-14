package org.example.apirest.service.service;


import org.example.apirest.dto.service.CreateServiceDto;
import org.example.apirest.dto.service.ServiceDto;
import org.example.apirest.model.Service;

import java.util.List;

public interface ServiceService {
    List<ServiceDto> findAll();
    ServiceDto findOne(Long id);
    List<Service> findAllNewest(int openSince);
    ServiceDto save(CreateServiceDto restaurant);
    ServiceDto update(Long id, CreateServiceDto restaurant);
    void delete(Long id);
}
