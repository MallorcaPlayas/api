package org.example.apirest;

import org.example.apirest.model.Beach;
import org.example.apirest.model.ServiceBeach;
import org.example.apirest.model.TypeBeach;
import org.example.apirest.repository.BeachRepository;
import org.example.apirest.repository.ServiceRepository;
import org.example.apirest.repository.TypeBeachRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ApiRestBaseApplication {

    public static void main(String[] args) {
        ApplicationContext context  = SpringApplication.run(ApiRestBaseApplication.class, args);

        BeachRepository beachRepository = context.getBean(BeachRepository.class);
        ServiceRepository serviceRepository = context.getBean(ServiceRepository.class);
        TypeBeachRepository typeBeachRepository = context.getBean(TypeBeachRepository.class);





        ServiceBeach service1 = new ServiceBeach("Toilet",LocalTime.of(8,0,0),LocalTime.of(20,0,0));
        ServiceBeach service2 = new ServiceBeach("Restaurant",LocalTime.of(5,0,0),LocalTime.of(23,0,0));
        ServiceBeach service3 = new ServiceBeach("Hotel",LocalTime.of(10,0,0),LocalTime.of(16,0,0));
        serviceRepository.save(service1);
        serviceRepository.save(service2);
        serviceRepository.save(service3);

        TypeBeach typeBeach1 = new TypeBeach("Rocas");
        TypeBeach typeBeach2 = new TypeBeach("Arena");
        TypeBeach typeBeach3 = new TypeBeach("Gravilla");
        typeBeachRepository.save(typeBeach1);
        typeBeachRepository.save(typeBeach2);
        typeBeachRepository.save(typeBeach3);

        List<ServiceBeach> serviceBeachList = new ArrayList<>();
        serviceBeachList.add(service1);
        serviceBeachList.add(service2);
        serviceBeachList.add(service3);

        List<TypeBeach> typeBeachList = new ArrayList<>();
        typeBeachList.add(typeBeach1);
        typeBeachList.add(typeBeach2);
        typeBeachList.add(typeBeach3);


        Beach beach1 = new Beach(null,"Fiufiu","Aqui es muy bien",serviceBeachList ,typeBeachList);
        beachRepository.save(beach1);
    }

}
