package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.billType.BillTypeDto;
import org.example.apirest.dto.billType.CreateBillTypeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/billtypes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BillTypeController {

    private final BillTypeService service;

    @GetMapping
    public ResponseEntity<List<BillTypeDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillTypeDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<BillTypeDto> create(@RequestBody CreateBillTypeDto billType) {
        BillTypeDto newBillType = service.save(billType);
        return ResponseEntity.created(URI.create("/api/billtypes/" + newBillType.getId())).body(newBillType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillTypeDto> update(@RequestBody CreateBillTypeDto billType, @PathVariable Long id) {
        BillTypeDto updatedBillType = service.update(id, billType);
        return ResponseEntity.created(URI.create("/api/billtypes/" + id)).body(updatedBillType);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
