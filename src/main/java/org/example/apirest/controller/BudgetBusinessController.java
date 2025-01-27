package org.example.apirest.controller;

import org.example.apirest.dto.budgetBusiness.BudgetBusinessDto;
import org.example.apirest.dto.budgetBusiness.CreateBudgetBusinessDto;
import org.example.apirest.service.budgetBusiness.BudgetBusinessServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/budget-business")
@CrossOrigin(origins = "*")
public class BudgetBusinessController extends GeneralizedController<BudgetBusinessDto, CreateBudgetBusinessDto> {
    public BudgetBusinessController(BudgetBusinessServiceImpl service) {
        super(service);
    }
}
