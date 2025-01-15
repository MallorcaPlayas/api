package org.example.apirest.service.businessType;

import org.example.apirest.dto.businessType.BusinessTypeDto;
import org.example.apirest.dto.businessType.CreateBusinessTypeDto;

import java.util.List;

public interface BusinessTypeService {
    List<BusinessTypeDto> findAll();
    BusinessTypeDto findOne(Long id);
    BusinessTypeDto save(CreateBusinessTypeDto businessType);
    BusinessTypeDto update(Long id, CreateBusinessTypeDto businessType);
    void delete(Long id);
}
