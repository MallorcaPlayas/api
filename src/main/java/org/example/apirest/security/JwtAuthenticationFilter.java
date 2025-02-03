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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// esta anotacion para que sirve:
// @Component -> Spring se encarga de crear el objeto por ti y
// te permite usarlo en cualquier parte del código sin que tengas que instanciarlo manualmente, es decir,
// Spring se encarga de la inyección de dependencias.

@Component
//  un filtro JWT que intercepta cada solicitud HTTP entrante, verifica si contiene un token JWT válido y, si es así, autentica al usuario
//  Este filtro se ejecuta antes de UsernamePasswordAuthenticationFilter, que es el filtro predeterminado de Spring Security para autenticar a los usuarios con nombre de usuario y contraseña
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtKeyProvider jwtKeyProvider;

    public JwtAuthenticationFilter(JwtKeyProvider jwtKeyProvider) {
        this.jwtKeyProvider = jwtKeyProvider;
    }

    private Key getSigningKey() {
        return jwtKeyProvider.getSigningKey();
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

            // Estoy haciendo un casting
            // y con "var" estoy diciendo que es una lista de Strings. "var" permite declarar variables locales sin especificar explícitamente su tipo
            // "var" Solo funciona en variables locales, dentro de un mét_odo
            // Procesar roles como lista de cadenas
            @SuppressWarnings("unchecked")
            var roles = (List<String>) claims.get("roles"); // Recupera los roles como lista de cadenas
            // Procesar roles como lista de cadenas
            @SuppressWarnings("unchecked")
            var functions = (List<String>) claims.get("functions"); // Recupera funciones

            if (username != null && roles != null && functions != null) {
                List<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // SimpleGrantedAuthority: Almacenar un rol o permiso que puede ser usado por Spring Security para tomar decisiones sobre la autorización. Definir qué rutas o recursos puede acceder un usuario según sus roles.
                        .toList();

// Convertir funciones a autoridades y combinar con roles
                List<SimpleGrantedAuthority> mutableAuthorities = new ArrayList<>(authorities);
                functions.forEach(function -> mutableAuthorities.add(new SimpleGrantedAuthority(function)));

                // Crear UserDetails y configurar el contexto de seguridad
                UserDetails userDetails = new User(username, "", mutableAuthorities);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, mutableAuthorities);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Añade detalles adicionales sobre la solicitud HTTP, como la dirección IP y el navegador del cliente (Crhome)

                SecurityContextHolder.getContext().setAuthentication(authentication); // Es una clase que Spring usa para almacenar información de seguridad (como el usuario autenticado) durante el ciclo de vida de la solicitud
            }
        } catch (Exception e) {
            System.out.println("JwtAuthenticationFilter: Error al procesar el token JWT -> " + e);
            e.printStackTrace();
            e.getMessage();
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response); // Permite que la solicitud continúe, pero el usuario no estará autenticado.
    }
}
