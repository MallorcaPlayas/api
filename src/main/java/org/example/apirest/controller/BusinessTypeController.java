package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.businessType.BusinessTypeDto;
import org.example.apirest.dto.businessType.CreateBusinessTypeDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.businessType.BusinessTypeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/business-types")
@CrossOrigin(origins = "*")
public class BusinessTypeController extends GeneralizedController<BusinessTypeDto, CreateBusinessTypeDto> {
    public BusinessTypeController(BusinessTypeServiceImpl service) {
        super(service);
    }
}
