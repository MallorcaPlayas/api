package org.example.apirest.controller;

import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.service.user.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
// UserController: Maneja las operaciones CRUD de usuarios (registro, actualización, eliminación, etc.).
// UserDto: Representa un usuario cuando se devuelve desde la API (por ejemplo, al consultar un usuario).
// CreateUserDto: Representa los datos necesarios para crear o actualizar un usuario
// Si necesitas agregar un endpoint específico para usuarios (por ejemplo, buscar usuarios por rol),
// puedes sobrescribir o extender los métodos de la clase base
public class UserController extends GeneralizedController<UserDto, CreateUserDto> {
    public UserController(UserServiceImpl service) {
        super(service); // Pasa la instancia del servicio a la clase padre (GeneralizedController).
    }
}
