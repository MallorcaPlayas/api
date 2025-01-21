package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.organization.OrganizationDto;
import org.example.apirest.dto.organization.CreateOrganizationDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.organization.OrganizationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@CrossOrigin(origins = "*")
public class OrganizationController extends GeneralizedController<OrganizationDto, CreateOrganizationDto> {
    public OrganizationController(OrganizationServiceImpl service) {
        super(service);
    }
}
