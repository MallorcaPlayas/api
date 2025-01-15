package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "routes")
public class Route implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private boolean isPrivate;
    private double distance;
    private double duration;
    private double elevation;
}
