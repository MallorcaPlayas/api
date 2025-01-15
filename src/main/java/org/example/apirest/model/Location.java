package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "locations")
public class Location implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Double coordinateX;
    private Double coordinateY;
}
