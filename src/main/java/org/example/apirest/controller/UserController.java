package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.dto.user.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {


    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserDto>> index() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody CreateUserDto user) {
        UserDto newUser = service.save(user);
        return ResponseEntity.created(URI.create("/api/playas/" + newUser.getId())).body(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@RequestBody CreateUserDto user,@PathVariable Long id) {
        UserDto updatedUser = service.update(id,user);
        return ResponseEntity.created(URI.create("/api/playas/" + id)).body(updatedUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
