package org.example.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ApiRestBaseApplication {

    public static void main(String[] args) {
        ApplicationContext context  = SpringApplication.run(ApiRestBaseApplication.class, args);
    }

}
