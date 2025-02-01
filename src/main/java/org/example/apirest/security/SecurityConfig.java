package org.example.apirest.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Le dice a Spring que esta clase contiene la configuración de seguridad
@RequiredArgsConstructor // Crea un constructor con todos los campos requeridos, en este caso jwtAuthenticationFilter
// SecurityConfig Decide qué rutas (URLs) son accesibles sin autenticación
// Define cómo se valida la identidad de un usuario
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    // Este mét_odo configura cómo manejar las solicitudes entrantes
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Permite acceso sin autenticación a /api/auth
                        .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // JWT no usa sesiones. Con JWT, no se usan sesiones en el servidor; toda la información del usuario está en el token
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // El filtro JWT intercepta todas las solicitudes para validar el token antes de permitir el acceso.

        return http.build();
    }

    @Bean
    // Este mét_odo define cómo se encriptan las contraseñas antes de guardarlas en la base de datos.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    // AuthenticationManager: Es como el jefe del sistema de seguridad que valida si un usuario puede acceder o no.
    // UserDetailsService: Un servicio que proporciona la información del usuario (nombre de usuario, contraseña y roles) al AuthenticationManager.
    // @Qualifier: Le dice a Spring cuál de las implementaciones de UserDetailsService usar.
    public AuthenticationManager authenticationManager(
            @Qualifier("userServiceImpl") UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService); // UserDetailsService: Un servicio que proporciona la información del usuario (nombre de usuario, contraseña y roles) al AuthenticationManager.
        provider.setPasswordEncoder(passwordEncoder()); // PasswordEncoder: Una herramienta para verificar que la contraseña que ingresó el usuario coincide con la encriptada en la base de datos.
        return new ProviderManager(provider); // El ProviderManager  decide si el usuario es válido o no
    }
}
