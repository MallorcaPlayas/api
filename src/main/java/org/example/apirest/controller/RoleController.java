package org.example.apirest.controller;


import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.role.CreateRoleDto;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.service.beach.BeachService;
import org.example.apirest.service.role.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService service;

    @GetMapping
    public ResponseEntity<List<RoleDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<RoleDto> create(@RequestBody CreateRoleDto role) {
        RoleDto newRole = service.save(role);
        return ResponseEntity.created(URI.create("/api/playas/" + newRole.getId())).body(newRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> update(@RequestBody CreateRoleDto role,@PathVariable Long id) {
        RoleDto updatedRole = service.update(id,role);
        return ResponseEntity.created(URI.create("/api/playas/" + id)).body(updatedRole);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
