package org.example.apirest.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table // (name = "roles")
public class Role implements BaseEntity{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long price;
    private String description;

    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
