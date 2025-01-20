package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.aggregationType.AggregationTypeDto;
import org.example.apirest.dto.aggregationType.CreateAggregationTypeDto;
import org.example.apirest.dto.userHasRole.CreateUserHasRoleDto;
import org.example.apirest.dto.userHasRole.UserHasRoleDto;
import org.example.apirest.service.aggregationType.AggregationTypeService;
import org.example.apirest.service.userHasRole.UserHasRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user-has-role")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserHasRoleController {

    private final UserHasRoleService service;

    @GetMapping
    public ResponseEntity<List<UserHasRoleDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserHasRoleDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<UserHasRoleDto> create(@RequestBody CreateUserHasRoleDto entity) {
        UserHasRoleDto newEntity = service.save(entity);

        System.out.println("User ID: " + newEntity.getUser());
        System.out.println("Role ID: " + newEntity.getRole());
        return ResponseEntity.created(URI.create("/api/aggregation-types/" + newEntity.getId())).body(newEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserHasRoleDto> update(@RequestBody CreateUserHasRoleDto entity, @PathVariable Long id) {
        UserHasRoleDto updatedEntity = service.update(id, entity);
        return ResponseEntity.created(URI.create("/api/aggregation-types/" + id)).body(updatedEntity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
