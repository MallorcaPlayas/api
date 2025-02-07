package org.example.apirest.controller;

import org.example.apirest.dto.billType.BillTypeDto;
import org.example.apirest.dto.billType.CreateBillTypeDto;
import org.example.apirest.service.billType.BillTypeServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billtypes")
@CrossOrigin(origins = "*")
public class BillTypeController extends GeneralizedController<BillTypeDto, CreateBillTypeDto> {
    public BillTypeController(BillTypeServiceImpl service) {
        super(service);
    }
}
