package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.billType.BillTypeDto;
import org.example.apirest.dto.billType.CreateBillTypeDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.bill.BillServiceImpl;
import org.example.apirest.service.billType.BillTypeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/billtypes")
@CrossOrigin(origins = "*")
public class BillTypeController extends GeneralizedController<BillTypeDto, CreateBillTypeDto>{
    public BillTypeController(BillTypeServiceImpl service) {
        super(service);
    }
}
