package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.business.BusinessDto;
import org.example.apirest.dto.business.CreateBusinessDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.business.BusinessServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/businesses")
@CrossOrigin(origins = "*")
public class BusinessController extends GeneralizedController<BusinessDto, CreateBusinessDto> {
    public BusinessController(BusinessServiceImpl service) {
        super(service);
    }
}
