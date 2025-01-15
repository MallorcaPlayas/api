package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aggregation_types")
public class AggregationType implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
