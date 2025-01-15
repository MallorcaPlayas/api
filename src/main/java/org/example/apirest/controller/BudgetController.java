package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.budget.BudgetDto;
import org.example.apirest.dto.budget.CreateBudgetDto;
import org.example.apirest.service.budget.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BudgetController {

    private final BudgetService service;

    @GetMapping
    public ResponseEntity<List<BudgetDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<BudgetDto> create(@RequestBody CreateBudgetDto budget) {
        BudgetDto newBudget = service.save(budget);
        return ResponseEntity.created(URI.create("/api/budgets/" + newBudget.getId())).body(newBudget);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetDto> update(@RequestBody CreateBudgetDto budget, @PathVariable Long id) {
        BudgetDto updatedBudget = service.update(id, budget);
        return ResponseEntity.created(URI.create("/api/budgets/" + id)).body(updatedBudget);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
