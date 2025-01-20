package org.example.apirest;

import org.example.apirest.model.*;
import org.example.apirest.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

@SpringBootApplication
public class ApiRestBaseApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ApiRestBaseApplication.class, args);

        // Obtener los repositorios
        UserRepository userRepository = context.getBean(UserRepository.class);
        RoleRepository roleRepository = context.getBean(RoleRepository.class);
        UserHasRoleRepository userHasRoleRepository = context.getBean(UserHasRoleRepository.class);
        UserRequireRoleRepository userRequireRoleRepository = context.getBean(UserRequireRoleRepository.class);

        // Nuevos repositorios
        BeachRepository beachRepository = context.getBean(BeachRepository.class);
        ServiceRepository serviceRepository = context.getBean(ServiceRepository.class);
        BeachHasServiceRepository beachHasServiceRepository = context.getBean(BeachHasServiceRepository.class);
        TypeBeachRepository typeBeachRepository = context.getBean(TypeBeachRepository.class);
        CameraRepository cameraRepository = context.getBean(CameraRepository.class);

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

        // Crear instancias de UserRequireRole
        UserRequireRole userRequireRole1 = new UserRequireRole();
        userRequireRole1.setUser(user1);
        userRequireRole1.setRole(adminRole);
        userRequireRole1.setUrlPhotoDni("https://example.com/dni/john_doe.jpg");
        userRequireRole1.setUrlOfficialDoc("https://example.com/docs/john_doe.pdf");
        userRequireRole1.setApproved(true);

        UserRequireRole userRequireRole2 = new UserRequireRole();
        userRequireRole2.setUser(user2);
        userRequireRole2.setRole(userRole);
        userRequireRole2.setUrlPhotoDni("https://example.com/dni/jane_doe.jpg");
        userRequireRole2.setUrlOfficialDoc("https://example.com/docs/jane_doe.pdf");
        userRequireRole2.setApproved(false); // No aprobado

        // Guardar las instancias de UserRequireRole
        userRequireRoleRepository.saveAll(Arrays.asList(userRequireRole1, userRequireRole2));

        // Crear instancias de las nuevas entidades

        // Crear tipos de playas
        TypeBeach typeBeach1 = new TypeBeach();
        typeBeach1.setName("Playa tranquila");

        TypeBeach typeBeach2 = new TypeBeach();
        typeBeach2.setName("Playa deportiva");

        // Guardar los tipos de playa
        typeBeachRepository.saveAll(Arrays.asList(typeBeach1, typeBeach2));

        // Crear playas
        Beach beach1 = new Beach();
        beach1.setName("Playa Bonita");
        beach1.setDescription("Una playa con arenas doradas y aguas cristalinas.");

        Beach beach2 = new Beach();
        beach2.setName("Playa del Sol");
        beach2.setDescription("Una playa famosa por su sol brillante y el clima cálido.");

        // Relacionar playas con tipos
        beach1.setTypes(Arrays.asList(typeBeach1));
        beach2.setTypes(Arrays.asList(typeBeach2));

        // Guardar las playas
        beachRepository.saveAll(Arrays.asList(beach1, beach2));

        // Crear servicios
        ServiceBeach service1 = new ServiceBeach();
        service1.setName("Alquiler de sombrillas");

        ServiceBeach service2 = new ServiceBeach();
        service2.setName("Alquiler de kayaks");

        // Guardar los servicios
        serviceRepository.saveAll(Arrays.asList(service1, service2));

        // Crear relaciones BeachHasService
        BeachHasService beachHasService1 = new BeachHasService();
        beachHasService1.setBeach(beach1);
        beachHasService1.setServiceBeach(service1);

        BeachHasService beachHasService2 = new BeachHasService();
        beachHasService2.setBeach(beach2);
        beachHasService2.setServiceBeach(service2);
        beachHasService2.setStartTime(LocalTime.of(1,1,1));
        beachHasService2.setEndTime(LocalTime.of(8,8,8));

        // Guardar las relaciones BeachHasService
        beachHasServiceRepository.saveAll(Arrays.asList(beachHasService1, beachHasService2));


        // Crear cámaras
        Camera camera1 = new Camera();
        camera1.setBeach(beach1);

        Camera camera2 = new Camera();
        camera2.setBeach(beach2);

        // Guardar las cámaras
        cameraRepository.saveAll(Arrays.asList(camera1, camera2));
    }
}
