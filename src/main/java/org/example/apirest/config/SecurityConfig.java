package org.example.apirest.config;

import lombok.RequiredArgsConstructor;
import org.example.apirest.security.JwtAuthenticationFilter;
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
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration // Le dice a Spring que esta clase contiene la configuración de seguridad
@RequiredArgsConstructor // Crea un constructor con todos los campos requeridos, en este caso jwtAuthenticationFilter
// SecurityConfig Decide qué rutas (URLs) son accesibles sin autenticación
// Define cómo se valida la identidad de un usuario
@EnableGlobalMethodSecurity(prePostEnabled = true)
// Habilita @PreAuthorize y @PostAuthorize para que puedas proteger los métodos de tus controladores
public class SecurityConfig {

    // Con @RequiredArgsConstructor, Lombok crea un constructor con todos los campos requeridos
    // En este caso, jwtAuthenticationFilter es el único campo requerido
    // @Component en la clase JwtAuthenticationFilter detecta que esta clase debe ser un bean y automáticamente inyecta su instancia aquí.
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    // Este mét_odo configura cómo manejar las solicitudes entrantes
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configuración de CORS
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/usersMongoDB", "/tranduccionMongoDB", "/**", "/list").permitAll() // Permite acceso sin autenticación a las ruta /api/auth
                        // Dejo el codigo de abajo comentado como ejemplo por si en un futuro lo uso
                        //  .requestMatchers("/api/users/**").hasAuthority("ReadUser") // Restringir acceso a `/api/users` a usuarios con la función ReadUser
                        .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configura la aplicación para no usar sesiones en el servidor (estado STATELESS). En lugar de almacenar información de sesión, to_do se gestiona mediante tokens JWT. En sistemas con JWT, la información del usuario (como su autenticación y roles) está contenida en el token
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Añade un filtro llamado jwtAuthenticationFilter que intercepta todas las solicitudes y valida el token JWT antes de permitir que lleguen al resto de la aplicación. El filtro se ejecuta antes de UsernamePasswordAuthenticationFilter, que es el filtro predeterminado de Spring Security para autenticar a los usuarios con nombre de usuario y contraseña

        return http.build(); // Para que sirve? -> Construye la configuración de seguridad y la devuelve. Spring Security la usará para proteger la aplicación cuando se inicie la aplicación en el servidor
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:81")); // Dominios permitidos
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos HTTP permitidos
        configuration.setAllowedHeaders(List.of("*")); // Encabezados permitidos
        configuration.setAllowCredentials(true); // Permitir credenciales (ej. Authorization)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    // Este mét_odo define cómo se encriptan las contraseñas antes de guardarlas en la base de datos.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCryptPasswordEncoder: Una implementación de PasswordEncoder que usa el algoritmo de encriptación BCrypt. BCrypt Es unidireccional: Una vez encriptada, la contraseña no se puede "desencriptar". Solo se puede comparar con otra contraseña encriptada para verificar si son iguales.
    }

    @Bean
    // AuthenticationManager: Es como el jefe del sistema de seguridad que valida si un usuario puede acceder o no.
    // UserDetailsService: Un servicio que proporciona la información del usuario (nombre de usuario, contraseña y roles) al AuthenticationManager.
    // @Qualifier: Le dice a Spring cuál de las implementaciones de UserDetailsService usar.
    // @Bean: Le dice a Spring que guarde el resultado de este mét_odo para que pueda ser inyectado en otras partes de la aplicación.
    public AuthenticationManager authenticationManager(@Qualifier("userServiceImpl") UserDetailsService userDetailsService) { // UserDetailsService es una interfaz que Spring Security usa para interactuar con la base de datos y obtener información del usuario y con @Qualifier("userServiceImpl") le decimos a Spring que use la implementación UserServiceImpl de UserDetailsService
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService); // UserDetailsService: Un servicio que proporciona la información del usuario (nombre de usuario, contraseña y roles) al AuthenticationManager.
        provider.setPasswordEncoder(passwordEncoder()); // PasswordEncoder: Una herramienta para verificar que la contraseña que ingresó el usuario coincide con la encriptada en la base de datos.
        return new ProviderManager(provider); // El ProviderManager  decide si el usuario es válido o no
    }
}
