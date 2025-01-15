package org.example.apirest.service.budget;

import org.example.apirest.dto.budget.BudgetDto;
import org.example.apirest.dto.budget.CreateBudgetDto;

import java.util.List;

public interface BudgetService {
    List<BudgetDto> findAll();
    BudgetDto findOne(Long id);
    BudgetDto save(CreateBudgetDto budget);
    BudgetDto update(Long id, CreateBudgetDto budget);
    void delete(Long id);
}
