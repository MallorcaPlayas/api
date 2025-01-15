package org.example.apirest.service.bill;

import org.example.apirest.dto.bill.BillDto;
import org.example.apirest.dto.bill.CreateBillDto;

import java.util.List;

public interface BillService {
    List<BillDto> findAll();
    BillDto findOne(Long id);
    BillDto save(CreateBillDto bill);
    BillDto update(Long id, CreateBillDto bill);
    void delete(Long id);
}
