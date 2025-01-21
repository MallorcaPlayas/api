package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.budget.BudgetDto;
import org.example.apirest.dto.budget.CreateBudgetDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.budget.BudgetServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@CrossOrigin(origins = "*")
public class BudgetController extends GeneralizedController<BudgetDto, CreateBudgetDto> {
    public BudgetController(BudgetServiceImpl service) {
        super(service);
    }
}
