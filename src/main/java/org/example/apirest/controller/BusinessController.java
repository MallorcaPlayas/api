package org.example.apirest.controller;

import org.example.apirest.dto.business.BusinessDto;
import org.example.apirest.dto.business.CreateBusinessDto;
import org.example.apirest.service.business.BusinessServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/businesses")
@CrossOrigin(origins = "*")
public class BusinessController extends GeneralizedController<BusinessDto, CreateBusinessDto> {
    public BusinessController(BusinessServiceImpl service) {
        super(service);
    }
}
