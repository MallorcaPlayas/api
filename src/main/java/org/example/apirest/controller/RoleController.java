package org.example.apirest.controller;


import org.example.apirest.dto.role.CreateRoleDto;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.service.role.RoleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "*")
public class RoleController extends GeneralizedController<RoleDto, CreateRoleDto> {
    public RoleController(RoleServiceImpl service) {
        super(service);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('readRole')")
    public ResponseEntity<List<RoleDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('readRole')")
    public ResponseEntity<RoleDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('createRole')")
    public ResponseEntity<RoleDto> create(@RequestBody CreateRoleDto entity) {
        RoleDto newEntity = service.save(entity);
        return ResponseEntity.ok(newEntity);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('updateRole')")
    public ResponseEntity<RoleDto> update(@RequestBody CreateRoleDto entity, @PathVariable Long id) {
        RoleDto updated = service.update(id, entity);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('deleteRole')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
