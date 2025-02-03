package org.example.apirest.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.security.JwtKeyProvider;
import org.example.apirest.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

@RestController // Todos los métodos devolverán respuestas HTTP. Por defecto, todos los métodos devolverán un estado 200 y en formato JSON.
@RequestMapping("/api/auth") // Todos los endpoints de este controlador tendrán la ruta /api/auth
@CrossOrigin(origins = "*") // Permite que los endpoints de este controlador puedan ser accedidos por cualquier dominio
public class AuthController {

    private final UserServiceImpl userService; // servicio para buscar y guardar usuarios en la base de datos
    private final PasswordEncoder passwordEncoder; // Clase de Spring Security para encriptar y verificar contraseñas
    private final JwtKeyProvider jwtKeyProvider;


    public AuthController(UserServiceImpl userService, PasswordEncoder passwordEncoder, JwtKeyProvider jwtKeyProvider) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtKeyProvider = jwtKeyProvider;
    }

    private Key getSigningKey() {
        return jwtKeyProvider.getSigningKey();
    }

    // Endpoint para iniciar sesión, y si to_do es correcto se genera un token JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        Optional<UserDto> userOptional = userService.findByUserName(username);
        if (userOptional.isPresent()) {
            UserDto user = userOptional.get();
            // Verifica si la contraseña coincide
            if (passwordEncoder.matches(password, user.getPassword())) {
                String token = Jwts.builder()
                        .setSubject(user.getUserName())
                        .claim("email", user.getEmail()) // Agregar el email
                // Pendiente añadir los roles o las funcionalidades
                        //        .claim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList())) // Agregar roles
                        .setIssuedAt(new Date()) // Fecha de creacion del token
                        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas expiración del token
                        .signWith(getSigningKey()) // Firma el token con la clave secreta
                        .compact(); // Compacta el token en una cadena

                return ResponseEntity.ok(token);
            }
        }

        return ResponseEntity.status(401).body("Usuario o contraseña incorrectos"); // Si no coincide, retorna error
    }


    // Endpoint para registrar un usuario
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateUserDto createUserDto) {
        System.out.println("paso por aqui para hacer el registro?");
        System.out.println(createUserDto.toString());
        UserDto savedUser = userService.save(createUserDto);
        return ResponseEntity.ok(savedUser);
    }
}
