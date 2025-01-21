package org.example.apirest.service.business;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.budget.BudgetDto;
import org.example.apirest.dto.budget.CreateBudgetDto;
import org.example.apirest.dto.business.BusinessDto;
import org.example.apirest.dto.business.CreateBusinessDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Budget;
import org.example.apirest.model.Business;
import org.example.apirest.repository.BudgetRepository;
import org.example.apirest.repository.BusinessRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl extends GeneralizedServiceImpl<Business, BusinessDto, CreateBusinessDto, BusinessRepository> {
    public BusinessServiceImpl(BusinessRepository repository, DtoConverterImpl<Business,BusinessDto,CreateBusinessDto> dtoConverter) {
        super(repository, dtoConverter, Business.class, BusinessDto.class);
    }
}
