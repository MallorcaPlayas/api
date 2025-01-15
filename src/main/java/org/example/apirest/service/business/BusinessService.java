package org.example.apirest.service.business;

import org.example.apirest.dto.business.BusinessDto;
import org.example.apirest.dto.business.CreateBusinessDto;

import java.util.List;

public interface BusinessService {
    List<BusinessDto> findAll();
    BusinessDto findOne(Long id);
    BusinessDto save(CreateBusinessDto business);
    BusinessDto update(Long id, CreateBusinessDto business);
    void delete(Long id);
}
