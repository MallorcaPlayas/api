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
@Table(name = "road_type")
public class RoadType implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    String name;

    @OneToMany(mappedBy = "segment")
    private List<Segment> segments;
}
