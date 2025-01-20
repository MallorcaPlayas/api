package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table //(name = "service")
public class ServiceBeach implements BaseEntity{
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "serviceBeach")
    private List<BeachHasService> beachHasServiceBeach;

}
