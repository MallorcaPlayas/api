package org.example.apirest.controller;

import org.example.apirest.dto.userRequireRole.CreateUserRequireRoleDto;
import org.example.apirest.dto.userRequireRole.UserRequireRoleDto;
import org.example.apirest.service.userRequireRole.UserRequireRoleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-require-role")
@CrossOrigin(origins = "*")
public class UserRequireRoleController extends GeneralizedController<UserRequireRoleDto, CreateUserRequireRoleDto> {
    public UserRequireRoleController(UserRequireRoleServiceImpl  service) {
        super(service);
    }
    // TODO esta clase sirve para denegar o aceptar solicitudes de roles

    @GetMapping
    @PreAuthorize("hasAuthority('readRole')")
    public ResponseEntity<List<UserRequireRoleDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('readRole')")
    public ResponseEntity<UserRequireRoleDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('createRole')")
    public ResponseEntity<UserRequireRoleDto> create(@RequestBody CreateUserRequireRoleDto entity) {
        UserRequireRoleDto newEntity = service.save(entity);
        return ResponseEntity.ok(newEntity);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('updateRole')")
    public ResponseEntity<UserRequireRoleDto> update(@RequestBody CreateUserRequireRoleDto entity, @PathVariable Long id) {
        UserRequireRoleDto updated = service.update(id, entity);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('deleteRole')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
