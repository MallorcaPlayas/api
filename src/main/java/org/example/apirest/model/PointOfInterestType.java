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
@Table(name = "point_of_interest_types")
public class PointOfInterestType{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "pointOfInterestType", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<PointOfInterest> pointOfInterests;
}
