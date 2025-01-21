package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.userRequireRole.CreateUserRequireRoleDto;
import org.example.apirest.dto.userRequireRole.UserRequireRoleDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user-require-role")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserRequireRoleController {

    private final UserRequireRoleService service;

    @GetMapping
    public ResponseEntity<List<UserRequireRoleDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRequireRoleDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<UserRequireRoleDto> create(@RequestBody CreateUserRequireRoleDto entity) {
        UserRequireRoleDto newEntity = service.save(entity);

        System.out.println("User ID: " + newEntity.getUser());
        System.out.println("Role ID: " + newEntity.getRole());
        return ResponseEntity.created(URI.create("/api/aggregation-types/" + newEntity.getId())).body(newEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRequireRoleDto> update(@RequestBody CreateUserRequireRoleDto entity, @PathVariable Long id) {
        UserRequireRoleDto updatedEntity = service.update(id, entity);
        return ResponseEntity.created(URI.create("/api/aggregation-types/" + id)).body(updatedEntity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
