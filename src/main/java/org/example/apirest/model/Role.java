package org.example.apirest.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table (name = "roles")
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long price;
    private String description;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<UserHasRole> userHasRoles;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<UserRequireRole> userRequireRoles;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<RoleHasFunction> roleHasFunctions;
}
