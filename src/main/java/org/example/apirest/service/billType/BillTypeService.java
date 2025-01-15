package org.example.apirest.service.billType;

import org.example.apirest.dto.billType.BillTypeDto;
import org.example.apirest.dto.billType.CreateBillTypeDto;

import java.util.List;

public interface BillTypeService {
    List<BillTypeDto> findAll();
    BillTypeDto findOne(Long id);
    BillTypeDto save(CreateBillTypeDto billType);
    BillTypeDto update(Long id, CreateBillTypeDto billType);
    void delete(Long id);
}
