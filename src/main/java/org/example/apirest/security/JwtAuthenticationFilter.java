package org.example.apirest.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;

// esta anotacion para que sirve:
// @Component -> Spring se encarga de crear el objeto por ti y
// te permite usarlo en cualquier parte del código sin que tengas que instanciarlo manualmente, es decir,
// Spring se encarga de la inyección de dependencias.

@Component
//  un filtro JWT que intercepta cada solicitud HTTP entrante, verifica si contiene un token JWT válido y, si es así, autentica al usuario
//  Este filtro se ejecuta antes de UsernamePasswordAuthenticationFilter, que es el filtro predeterminado de Spring Security para autenticar a los usuarios con nombre de usuario y contraseña
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}") // Inyecta el valor de la propiedad jwt.secret del archivo application.properties
    private String SECRET_KEY; // Clave secreta para firmar (cuando se genera) y verificar el token JWT que no ha sido manipulado

    private Key getSigningKey() { // Generar una clave criptográfica (Key) a partir de una clave secreta en Base64.
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    // Este méto_do se ejecuta para cada solicitud HTTP entrante. Verifica si la solicitud contiene un token JWT válido y, si es así, autentica al usuario.
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization"); // Obtiene el encabezado Authorization de la solicitud. Este encabezado contiene el token JWT

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // Si no hay un token JWT en el encabezado Authorization, la solicitud se envía al siguiente filtro
            return;
        }

        String token = authHeader.substring(7); // Elimina "Bearer "
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey()) // Usa la clave secreta (obtenida de getSigningKey()) para validar el toke JWT
                    .build() //Si el token es válido, extrae sus claims (datos almacenados en el token). Los claims son pares clave-valor que contienen información sobre el usuario autenticado
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject(); // Obtiene el nombre de usuario del claim subject del token JWT
            if (username != null) {
                UserDetails userDetails = new User(username, "", Collections.emptyList()); // Crea un objeto UserDetails con el nombre de usuario y sin contraseña ni roles
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); // Crea un objeto UsernamePasswordAuthenticationToken con el usuario autenticado, sin credenciales y con los roles del usuario

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Añade detalles adicionales sobre la solicitud HTTP, como la dirección IP y el navegador del cliente (Crhome)

                SecurityContextHolder.getContext().setAuthentication(authentication); // Es una clase que Spring usa para almacenar información de seguridad (como el usuario autenticado) durante el ciclo de vida de la solicitud
            }
        } catch (Exception e) {
            System.out.println("JwtAuthenticationFilter: Error al procesar el token JWT -> " + e.getMessage());
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response); // Continúa con el siguiente filtro
    }
}
