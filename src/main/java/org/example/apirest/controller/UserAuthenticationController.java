package org.example.apirest.controller;

import io.jsonwebtoken.Jwts;
import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.security.JwtKeyProvider;
import org.example.apirest.service.user.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController // Todos los métodos devolverán respuestas HTTP. Por defecto, todos los métodos devolverán un estado 200 y en formato JSON.
@RequestMapping("/api/auth") // Todos los endpoints de este controlador tendrán la ruta /api/auth
@CrossOrigin(origins = "*") // Permite que los endpoints de este controlador puedan ser accedidos por cualquier dominio
public class UserAuthenticationController {

    private final UserServiceImpl userService; // servicio para buscar y guardar usuarios en la base de datos
    private final PasswordEncoder passwordEncoder; // Clase de Spring Security para encriptar y verificar contraseñas
    private final JwtKeyProvider jwtKeyProvider;


    public UserAuthenticationController(UserServiceImpl userService, PasswordEncoder passwordEncoder, JwtKeyProvider jwtKeyProvider) {
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

        // comprobar si es username o email
        Optional<UserDto> userOptional;
        if (username.contains("@")) {
            System.out.println("es un email");
            userOptional = userService.findByEmail(username);
        } else {
            System.out.println("es un username");
            userOptional = userService.findByUserName(username);
        }


        if (userOptional.isPresent()) {
            UserDto user = userOptional.get();
            // Verifica si la contraseña coincide
            if (passwordEncoder.matches(password, user.getPassword())) {


                // Extraer los nombres de los roles como cadenas
                List<String> roleNames = user.getRoles().stream()
                        .map(role -> role.getRole().getName()) // Obtener solo el nombre del rol
                        .toList();


                // Extraer las funciones de ese ROL como cadenas
                List<String> functions = user.getRoles().stream()
                        .flatMap(role -> role.getRole().getRoleHasFunctions().stream()) // Accede a las funciones del rol
                        .map(function -> function.getFunction().getName()) // Obtén el nombre de cada función
                        .toList();

                String token = Jwts.builder()
                        .setSubject(user.getUserName())
                        .claim("email", user.getEmail()) // Agregar el email
                        .claim("roles", roleNames) // Agregar nombres de roles al token
                        .claim("functions", functions) // Agregar funciones
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
