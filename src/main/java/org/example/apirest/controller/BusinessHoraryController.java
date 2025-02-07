package org.example.apirest.controller;

import org.example.apirest.dto.businessHorary.BusinessHoraryDto;
import org.example.apirest.dto.businessHorary.CreateBusinessHoraryDto;
import org.example.apirest.service.businessHorary.BusinessHoraryServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/businesses-horaries")
@CrossOrigin(origins = "*")
public class BusinessHoraryController extends GeneralizedController<BusinessHoraryDto, CreateBusinessHoraryDto> {
    public BusinessHoraryController(BusinessHoraryServiceImpl service) {
        super(service);
    }
}
