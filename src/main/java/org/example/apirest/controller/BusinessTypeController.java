package org.example.apirest.controller;

import org.example.apirest.dto.businessType.BusinessTypeDto;
import org.example.apirest.dto.businessType.CreateBusinessTypeDto;
import org.example.apirest.service.businessType.BusinessTypeServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business-types")
@CrossOrigin(origins = "*")
public class BusinessTypeController extends GeneralizedController<BusinessTypeDto, CreateBusinessTypeDto> {
    public BusinessTypeController(BusinessTypeServiceImpl service) {
        super(service);
    }
}
