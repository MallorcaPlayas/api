package org.example.apirest.controller;

import org.example.apirest.dto.budget.BudgetDto;
import org.example.apirest.dto.budget.CreateBudgetDto;
import org.example.apirest.service.budget.BudgetServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budgets")
@CrossOrigin(origins = "*")
public class BudgetController extends GeneralizedController<BudgetDto, CreateBudgetDto> {
    public BudgetController(BudgetServiceImpl service) {
        super(service);
    }
}
