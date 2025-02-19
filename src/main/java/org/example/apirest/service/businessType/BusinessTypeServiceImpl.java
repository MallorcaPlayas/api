package org.example.apirest.service.businessType;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.businessType.BusinessTypeDto;
import org.example.apirest.dto.businessType.CreateBusinessTypeDto;
import org.example.apirest.model.BusinessType;
import org.example.apirest.repository.BusinessTypeRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BusinessTypeServiceImpl extends GeneralizedServiceImpl<BusinessType, BusinessTypeDto, CreateBusinessTypeDto, BusinessTypeRepository> {
    public BusinessTypeServiceImpl(BusinessTypeRepository repository, DtoConverterGeneralizedImpl<BusinessType,BusinessTypeDto,CreateBusinessTypeDto> dtoConverter) {
        super(repository, dtoConverter, BusinessType.class, BusinessTypeDto.class);
    }
}
