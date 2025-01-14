package org.example.apirest.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Beach{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "beach_service",
            joinColumns = @JoinColumn(name = "beach_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    @JsonManagedReference
    private List<ServiceBeach> services;

    @ManyToMany
    @JoinTable(
            name = "beach_type", // Name of the join table
            joinColumns = @JoinColumn(name = "beach_id"), // Foreign key for Beach
            inverseJoinColumns = @JoinColumn(name = "type_id") // Foreign key for TypeBeach
    )
    @JsonManagedReference
    private List<TypeBeach> types;
}
