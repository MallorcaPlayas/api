package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.bill.BillDto;
import org.example.apirest.dto.bill.CreateBillDto;
import org.example.apirest.service.bill.BillServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BillController{
    private final BillServiceImpl service;

    @GetMapping
    public ResponseEntity<List<BillDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<BillDto> create(@RequestBody CreateBillDto entity) {
        BillDto newEntity = service.save(entity);
        return ResponseEntity.ok(newEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillDto> update(@RequestBody CreateBillDto entity, @PathVariable Long id) {
        BillDto updated = service.update(id, entity);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
