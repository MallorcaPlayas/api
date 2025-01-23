package org.example.apirest;

import org.example.apirest.model.*;
import org.example.apirest.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class ApiRestBaseApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ApiRestBaseApplication.class, args);
    }
}
