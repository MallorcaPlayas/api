package org.example.apirest.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
