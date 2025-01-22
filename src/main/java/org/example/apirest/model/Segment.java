package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "segment")
public class Segment implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Location locationOne;

    @OneToOne
    private Location locationTwo;
}
