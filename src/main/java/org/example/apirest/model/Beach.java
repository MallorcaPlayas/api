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
@Table // (name = "beaches")
public class Beach {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn //(name = "beach_has_service")
    private List<Service> services;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn //(name = "beach_has_types")
    private List<TypeBeach> types;
}
