package org.example.apirest.service.budget;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.budget.BudgetDto;
import org.example.apirest.dto.budget.CreateBudgetDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Budget;
import org.example.apirest.repository.BudgetRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository repository;
    private final DtoConverterImpl<Budget, BudgetDto, CreateBudgetDto> dtoConverter;

    @Override
    public List<BudgetDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), BudgetDto.class);
    }

    @Override
    public BudgetDto findOne(Long id) {
        Budget budget = repository.findById(id).orElseThrow(() -> new NotFoundException(Budget.class, id));
        return dtoConverter.convertDto(budget, BudgetDto.class);
    }

    @Override
    public BudgetDto save(CreateBudgetDto budget) {
        Budget budgetToInsert = dtoConverter.convertToEntityFromCreateDto(budget, Budget.class);
        return dtoConverter.convertDto(repository.save(budgetToInsert), BudgetDto.class);
    }

    @Override
    public BudgetDto update(Long id, CreateBudgetDto budget) {
        Budget oldBudget = repository.findById(id).orElseThrow(() -> new NotFoundException(Budget.class, id));
        Budget budgetToInsert = dtoConverter.convertToEntityFromCreateDto(budget, Budget.class);

        if (oldBudget == null) {
            return null;
        }

        UtilsClass.updateFields(oldBudget, budgetToInsert);

        return dtoConverter.convertDto(repository.save(oldBudget), BudgetDto.class);
    }

    @Override
    public void delete(Long id) {
        Budget budget = repository.findById(id).orElseThrow(() -> new NotFoundException(Budget.class, id));
        repository.delete(budget);
    }
}
