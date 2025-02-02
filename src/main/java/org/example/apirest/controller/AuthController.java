package org.example.apirest.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public AuthController(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 🔐 Endpoint para iniciar sesión y generar el token JWT
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        System.out.println("paso por aqui para hacer el login?");
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
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                        .signWith(getSigningKey())
                        .compact();

                return ResponseEntity.ok(token);
            }
        }

        return ResponseEntity.status(401).body("Usuario o contraseña incorrectos"); // Si no coincide, retorna error
    }


    // 📝 Endpoint para registrar un usuario
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateUserDto createUserDto) {
        System.out.println("paso por aqui para hacer el registro?");
        System.out.println(createUserDto.toString());
        UserDto savedUser = userService.save(createUserDto);
        return ResponseEntity.ok(savedUser);
    }
}
