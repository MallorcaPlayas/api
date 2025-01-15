package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "point_of_interest_types")
public class PointOfInterestType implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
