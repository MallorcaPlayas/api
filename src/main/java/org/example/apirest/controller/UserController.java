package org.example.apirest.controller;

import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.dto.user.UsertDtoUpdate;
import org.example.apirest.dto.user.UsertDtoV2;
import org.example.apirest.service.user.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
// UserController: Maneja las operaciones CRUD de usuarios (registro, actualización, eliminación, etc.).
// UserDto: Representa un usuario cuando se devuelve desde la API (por ejemplo, al consultar un usuario).
// CreateUserDto: Representa los datos necesarios para crear o actualizar un usuario
// Si necesitas agregar un endpoint específico para usuarios (por ejemplo, buscar usuarios por rol),
// puedes sobrescribir o extender los métodos de la clase base
public class UserController  {

    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('readUser')")
    public ResponseEntity<List<UserDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/v2")
    @PreAuthorize("hasAuthority('readUser')")
    public ResponseEntity<List<UsertDtoV2>> getAllUser() {
        return ResponseEntity.ok(service.findAllv2());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('readUser')")
    public ResponseEntity<UserDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('createUser')")
    public ResponseEntity<UserDto> create(@RequestBody CreateUserDto entity) {
        UserDto newEntity = service.save(entity);
        return ResponseEntity.ok(newEntity);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('updateUser')")
    public ResponseEntity<UserDto> update(@RequestBody CreateUserDto entity, @PathVariable Long id) {
        UserDto updated = service.update(id, entity);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/v2/{id}")
    @PreAuthorize("hasAuthority('updateUser')")
    public ResponseEntity<UserDto> updateUser(@RequestBody UsertDtoUpdate usertDtoUpdate, @PathVariable String id) {
        System.out.println("Entra la peticioón?");
        System.out.println("tenemos la id bien?" + id);

        System.out.println("tenemos la entiy bien?" + usertDtoUpdate.toString());
        Long idLong = Long.parseLong(id);


//        UserDto updated = service.update(idLong, usertDtoUpdate);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('deleteUser')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
