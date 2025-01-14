package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table //(name = "service")
public class Service {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    //@Column(name= "start_time")
    private LocalTime startTime;

    //@Column(name= "end_time")
    private LocalTime endTime;
}
