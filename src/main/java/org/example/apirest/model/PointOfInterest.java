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
public class PointOfInterest{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String url;

    @ManyToOne
    @JoinColumn(name = "point_of_interest_type_id")
    private PointOfInterestType pointOfInterestType;
}
