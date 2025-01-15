package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.organization.OrganizationDto;
import org.example.apirest.dto.organization.CreateOrganizationDto;
import org.example.apirest.service.organization.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrganizationController {

    private final OrganizationService service;

    @GetMapping
    public ResponseEntity<List<OrganizationDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<OrganizationDto> create(@RequestBody CreateOrganizationDto organization) {
        OrganizationDto newOrganization = service.save(organization);
        return ResponseEntity.created(URI.create("/api/organizations/" + newOrganization.getId())).body(newOrganization);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizationDto> update(@RequestBody CreateOrganizationDto organization, @PathVariable Long id) {
        OrganizationDto updatedOrganization = service.update(id, organization);
        return ResponseEntity.created(URI.create("/api/organizations/" + id)).body(updatedOrganization);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
