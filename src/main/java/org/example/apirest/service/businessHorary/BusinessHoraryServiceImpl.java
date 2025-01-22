package org.example.apirest.service.businessHorary;

import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.businessHorary.BusinessHoraryDto;
import org.example.apirest.dto.businessHorary.CreateBusinessHoraryDto;
import org.example.apirest.model.BusinessHorary;
import org.example.apirest.repository.BusinessHoraryRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BusinessHoraryServiceImpl extends GeneralizedServiceImpl<BusinessHorary, BusinessHoraryDto, CreateBusinessHoraryDto, BusinessHoraryRepository> {
    public BusinessHoraryServiceImpl(BusinessHoraryRepository repository, DtoConverterImpl<BusinessHorary,BusinessHoraryDto,CreateBusinessHoraryDto> dtoConverter) {
        super(repository, dtoConverter, BusinessHorary.class, BusinessHoraryDto.class);
    }
}
