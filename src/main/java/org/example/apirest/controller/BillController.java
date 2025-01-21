package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.bill.BillDto;
import org.example.apirest.dto.bill.CreateBillDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BillController {

    private final BillService service;

    @GetMapping
    public ResponseEntity<List<BillDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<BillDto> create(@RequestBody CreateBillDto bill) {
        BillDto newBill = service.save(bill);
        return ResponseEntity.created(URI.create("/api/bills/" + newBill.getId())).body(newBill);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillDto> update(@RequestBody CreateBillDto bill, @PathVariable Long id) {
        BillDto updatedBill = service.update(id, bill);
        return ResponseEntity.created(URI.create("/api/bills/" + id)).body(updatedBill);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
