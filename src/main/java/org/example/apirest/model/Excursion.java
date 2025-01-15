package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "excursions")
public class Excursion implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String description;
    private Date creationDate;
}
