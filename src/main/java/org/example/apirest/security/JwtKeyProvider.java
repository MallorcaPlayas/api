package org.example.apirest.security;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
@Component
public class JwtKeyProvider {

    @Value("${jwt.secret}") // Inyecta el valor de la propiedad jwt.secret del archivo application.properties
    private String SECRET_KEY; // Clave secreta para firmar (cuando se genera) y verificar el token JWT que no ha sido manipulado

    public Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
