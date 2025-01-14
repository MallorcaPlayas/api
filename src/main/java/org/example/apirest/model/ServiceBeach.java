package org.example.apirest.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table //(name = "service")
public class ServiceBeach{
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    //@Column(name= "start_time")
    private LocalTime startTime;

    //@Column(name= "end_time")
    private LocalTime endTime;

    @JsonManagedReference
    @ManyToMany(mappedBy = "services")
    private List<Beach> beaches;

    public ServiceBeach(String name, LocalTime startTime, LocalTime endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
