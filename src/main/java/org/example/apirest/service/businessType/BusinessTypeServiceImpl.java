package org.example.apirest.service.businessType;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.business.BusinessDto;
import org.example.apirest.dto.business.CreateBusinessDto;
import org.example.apirest.dto.businessType.BusinessTypeDto;
import org.example.apirest.dto.businessType.CreateBusinessTypeDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Business;
import org.example.apirest.model.BusinessType;
import org.example.apirest.repository.BusinessRepository;
import org.example.apirest.repository.BusinessTypeRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessTypeServiceImpl extends GeneralizedServiceImpl<BusinessType, BusinessTypeDto, CreateBusinessTypeDto, BusinessTypeRepository> {
    public BusinessTypeServiceImpl(BusinessTypeRepository repository, DtoConverterImpl<BusinessType,BusinessTypeDto,CreateBusinessTypeDto> dtoConverter) {
        super(repository, dtoConverter, BusinessType.class, BusinessTypeDto.class);
    }
}
