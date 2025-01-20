package org.example.apirest;

import org.example.apirest.model.*;
import org.example.apirest.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class ApiRestBaseApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ApiRestBaseApplication.class, args);

        // Obtener los repositorios
        UserRepository userRepository = context.getBean(UserRepository.class);
        RoleRepository roleRepository = context.getBean(RoleRepository.class);
        UserHasRoleRepository userHasRoleRepository = context.getBean(UserHasRoleRepository.class);

        // Crear roles
        Role adminRole = new Role();
        adminRole.setName("ADMIN");

        Role userRole = new Role();
        userRole.setName("USER");

        // Guardar los roles
        roleRepository.saveAll(Arrays.asList(adminRole, userRole));

        // Crear usuarios
        User user1 = new User();
        user1.setUserName("john_doe");

        User user2 = new User();
        user2.setUserName("jane_doe");

        // Crear la relación UserRole con fechas
        UserHasRole userRole1 = new UserHasRole();
        userRole1.setUser(user1);
        userRole1.setRole(adminRole);
        userRole1.setDateBegin(LocalDate.of(2025, 1, 1));
        userRole1.setDateFinish(LocalDate.of(2025, 12, 31));

        UserHasRole userRole2 = new UserHasRole();
        userRole2.setUser(user1);
        userRole2.setRole(userRole);
        userRole2.setDateBegin(LocalDate.of(2025, 1, 1));
        userRole2.setDateFinish(LocalDate.of(2025, 12, 31));

        UserHasRole userRole3 = new UserHasRole();
        userRole3.setUser(user2);
        userRole3.setRole(userRole);
        userRole3.setDateBegin(LocalDate.of(2025, 1, 1));
        userRole3.setDateFinish(null); // Usuario activo indefinidamente

        // Guardar los usuarios y sus roles
        userRepository.saveAll(Arrays.asList(user1, user2));
        userHasRoleRepository.saveAll(Arrays.asList(userRole1, userRole2, userRole3));
    }
}
