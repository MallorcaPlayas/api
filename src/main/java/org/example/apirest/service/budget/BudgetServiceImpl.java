package org.example.apirest.service.budget;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.budget.BudgetDto;
import org.example.apirest.dto.budget.CreateBudgetDto;
import org.example.apirest.model.Budget;
import org.example.apirest.repository.BudgetRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BudgetServiceImpl extends GeneralizedServiceImpl<Budget, BudgetDto, CreateBudgetDto, BudgetRepository> {
    public BudgetServiceImpl(BudgetRepository repository, DtoConverterGeneralizedImpl<Budget,BudgetDto,CreateBudgetDto> dtoConverter) {
        super(repository, dtoConverter, Budget.class, BudgetDto.class);
    }
}
