package org.example.apirest.controller;

import org.example.apirest.dto.organization.OrganizationDto;
import org.example.apirest.dto.organization.CreateOrganizationDto;
import org.example.apirest.service.organization.OrganizationServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organizations")
@CrossOrigin(origins = "*")
public class OrganizationController extends GeneralizedController<OrganizationDto, CreateOrganizationDto> {
    public OrganizationController(OrganizationServiceImpl service) {
        super(service);
    }
}
