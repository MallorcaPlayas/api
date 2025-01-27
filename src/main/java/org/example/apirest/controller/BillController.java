package org.example.apirest.controller;

import org.example.apirest.dto.bill.BillDto;
import org.example.apirest.dto.bill.CreateBillDto;
import org.example.apirest.service.bill.BillServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "*")
public class BillController extends GeneralizedController<BillDto, CreateBillDto> {
    public BillController(BillServiceImpl service) {
        super(service);
    }
}
