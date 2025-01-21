package org.example.apirest.service.budget;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.billType.BillTypeDto;
import org.example.apirest.dto.billType.CreateBillTypeDto;
import org.example.apirest.dto.budget.BudgetDto;
import org.example.apirest.dto.budget.CreateBudgetDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.BillType;
import org.example.apirest.model.Budget;
import org.example.apirest.repository.BillTypeRepository;
import org.example.apirest.repository.BudgetRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetServiceImpl extends GeneralizedServiceImpl<Budget, BudgetDto, CreateBudgetDto, BudgetRepository> {
    public BudgetServiceImpl(BudgetRepository repository, DtoConverterImpl<Budget,BudgetDto,CreateBudgetDto> dtoConverter) {
        super(repository, dtoConverter, Budget.class, BudgetDto.class);
    }
}
