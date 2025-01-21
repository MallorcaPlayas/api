package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.bill.BillDto;
import org.example.apirest.dto.bill.CreateBillDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.bill.BillServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "*")
public class BillController extends GeneralizedController<BillDto, CreateBillDto> {
    public BillController(BillServiceImpl service) {
        super(service);
    }
}
