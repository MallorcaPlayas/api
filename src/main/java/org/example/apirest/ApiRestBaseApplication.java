package org.example.apirest;

import org.example.apirest.model.*;
import org.example.apirest.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ApiRestBaseApplication {

    public static void main(String[] args) {
        ApplicationContext context  = SpringApplication.run(ApiRestBaseApplication.class, args);

        BeachRepository beachRepository = context.getBean(BeachRepository.class);
        ServiceRepository serviceRepository = context.getBean(ServiceRepository.class);
        TypeBeachRepository typeBeachRepository = context.getBean(TypeBeachRepository.class);
        RoleRepository roleRepository = context.getBean(RoleRepository.class);
        UserRepository userRepository = context.getBean(UserRepository.class);


        ServiceBeach service1 = new ServiceBeach("Toilet", LocalTime.of(8, 0), LocalTime.of(20, 0));
        ServiceBeach service2 = new ServiceBeach("Restaurant", LocalTime.of(5, 0), LocalTime.of(23, 0));
        ServiceBeach service3 = new ServiceBeach("Hotel", LocalTime.of(10, 0), LocalTime.of(16, 0));
        ServiceBeach service4 = new ServiceBeach("Bar", LocalTime.of(9, 0), LocalTime.of(22, 0));
        ServiceBeach service5 = new ServiceBeach("Spa", LocalTime.of(10, 0), LocalTime.of(19, 0));
        ServiceBeach service6 = new ServiceBeach("Parking", LocalTime.of(7, 0), LocalTime.of(22, 0));
        ServiceBeach service7 = new ServiceBeach("Lifeguard", LocalTime.of(8, 0), LocalTime.of(18, 0));
        ServiceBeach service8 = new ServiceBeach("Playground", LocalTime.of(9, 0), LocalTime.of(20, 0));
        ServiceBeach service9 = new ServiceBeach("WiFi", LocalTime.of(8, 0), LocalTime.of(20, 0));
        ServiceBeach service10 = new ServiceBeach("Shower", LocalTime.of(7, 0), LocalTime.of(20, 0));

        serviceRepository.saveAll(List.of(service1, service2, service3, service4, service5, service6, service7, service8, service9, service10));

        TypeBeach type1 = new TypeBeach("Rocks");
        TypeBeach type2 = new TypeBeach("Sand");
        TypeBeach type3 = new TypeBeach("Gravel");
        TypeBeach type4 = new TypeBeach("Cliffs");
        TypeBeach type5 = new TypeBeach("Coral");
        TypeBeach type6 = new TypeBeach("Pebbles");
        TypeBeach type7 = new TypeBeach("Cove");
        TypeBeach type8 = new TypeBeach("Island");
        TypeBeach type9 = new TypeBeach("Lagoon");
        TypeBeach type10 = new TypeBeach("Marsh");

        typeBeachRepository.saveAll(List.of(type1, type2, type3, type4, type5, type6, type7, type8, type9, type10));

        Beach beach1 = new Beach(null, "Fiufiu", "Beautiful beach with fine sand", List.of(service1, service2), List.of(type1, type2));
        Beach beach2 = new Beach(null, "Paradise Bay", "Calm waters and great for families", List.of(service3, service4), List.of(type3, type4));
        Beach beach3 = new Beach(null, "Coral Reef", "Excellent spot for diving", List.of(service5, service6), List.of(type5, type6));
        Beach beach4 = new Beach(null, "Sunny Beach", "Warm weather all year round", List.of(service7, service8), List.of(type7, type8));
        Beach beach5 = new Beach(null, "Blue Lagoon", "Known for its crystal-clear water", List.of(service9, service10), List.of(type9, type10));
        Beach beach6 = new Beach(null, "Sandy Shores", "Great for surfing", List.of(service1, service3), List.of(type2, type6));
        Beach beach7 = new Beach(null, "Rocky Point", "Perfect for adventurers", List.of(service4, service5), List.of(type4, type7));
        Beach beach8 = new Beach(null, "Crystal Beach", "Famous for its clean water", List.of(service6, service7), List.of(type3, type8));
        Beach beach9 = new Beach(null, "Seaside Retreat", "A quiet beach for relaxation", List.of(service8, service9), List.of(type5, type9));
        Beach beach10 = new Beach(null, "Ocean Breeze", "Cool breezes and beautiful views", List.of(service10, service2), List.of(type6, type10));

        beachRepository.saveAll(List.of(beach1, beach2, beach3, beach4, beach5, beach6, beach7, beach8, beach9, beach10));

        Role adminRole = new Role(null, "Admin", 1000L, "Administrator with full access", null);
        Role guiaRole = new Role(null, "Guia", 300L, "Guide with access to specific beach areas", null);
        Role socorristaRole = new Role(null, "Socorrista", 500L, "Lifeguard with safety responsibilities", null);
        Role gratisRole = new Role(null, "Gratis", 0L, "Free access with basic permissions", null);
        Role premiumRole = new Role(null, "Premium", 800L, "User with premium access and benefits", null);

        roleRepository.saveAll(Arrays.asList(adminRole, guiaRole, socorristaRole, gratisRole, premiumRole));

        User user1 = new User(
                null, "John Doe", "John", "Doe", "Smith", new Date(), "password123", "url/photo1", false, true,
                Arrays.asList(adminRole, guiaRole)
        );
        User user2 = new User(
                null, "Jane Roe", "Jane", "Roe", "Johnson", new Date(), "password456", "url/photo2", true, true,
                Arrays.asList(socorristaRole, gratisRole)
        );
        User user3 = new User(
                null, "Alice Green", "Alice", "Green", "Taylor", new Date(), "password789", "url/photo3", false, true,
                Arrays.asList(premiumRole)
        );
        User user4 = new User(
                null, "Bob Black", "Bob", "Black", "Brown", new Date(), "password012", "url/photo4", true, false,
                Arrays.asList(socorristaRole, gratisRole)
        );
        User user5 = new User(
                null, "Charlie White", "Charlie", "White", "Clark", new Date(), "password345", "url/photo5", false, false,
                Arrays.asList(adminRole, premiumRole)
        );

        // Save users to the database
        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
    }
}
