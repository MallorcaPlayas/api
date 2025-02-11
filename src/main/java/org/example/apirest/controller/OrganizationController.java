package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.organization.OrganizationDto;
import org.example.apirest.dto.organization.CreateOrganizationDto;
import org.example.apirest.service.organization.OrganizationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationServiceImpl service;

    @GetMapping
    public ResponseEntity<List<OrganizationDto>> index() {
        List<OrganizationDto> organizations = service.findAll();
        return ResponseEntity.ok(organizations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDto> show(@PathVariable Long id) {
        OrganizationDto organization = service.findOne(id);
        return ResponseEntity.ok(organization);
    }

    @PostMapping
    public ResponseEntity<OrganizationDto> create(@RequestBody CreateOrganizationDto dto) {
        OrganizationDto newOrganization = service.save(dto);
        return ResponseEntity.ok(newOrganization);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizationDto> update(@PathVariable Long id, @RequestBody CreateOrganizationDto dto) {
        OrganizationDto updatedOrganization = service.update(id, dto);
        return ResponseEntity.ok(updatedOrganization);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
