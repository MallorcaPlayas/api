package org.example.apirest.service.business;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.business.BusinessDto;
import org.example.apirest.dto.business.CreateBusinessDto;
import org.example.apirest.model.Business;
import org.example.apirest.repository.BusinessRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl extends GeneralizedServiceImpl<Business, BusinessDto, CreateBusinessDto, BusinessRepository> {
    public BusinessServiceImpl(BusinessRepository repository, DtoConverterGeneralizedImpl<Business,BusinessDto,CreateBusinessDto> dtoConverter) {
        super(repository, dtoConverter, Business.class, BusinessDto.class);
    }
}
