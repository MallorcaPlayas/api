package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "points_of_interest")
public class PointOfInterest implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private String url;
}
