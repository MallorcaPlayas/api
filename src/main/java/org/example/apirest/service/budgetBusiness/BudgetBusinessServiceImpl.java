package org.example.apirest.service.budgetBusiness;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.budgetBusiness.BudgetBusinessDto;
import org.example.apirest.dto.budgetBusiness.CreateBudgetBusinessDto;
import org.example.apirest.model.BudgetBusiness;
import org.example.apirest.repository.BudgetBusinessRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BudgetBusinessServiceImpl extends GeneralizedServiceImpl<BudgetBusiness, BudgetBusinessDto, CreateBudgetBusinessDto, BudgetBusinessRepository> {
    public BudgetBusinessServiceImpl(BudgetBusinessRepository repository, DtoConverterGeneralizedImpl<BudgetBusiness,BudgetBusinessDto,CreateBudgetBusinessDto> dtoConverter) {
        super(repository, dtoConverter, BudgetBusiness.class, BudgetBusinessDto.class);
    }
}
