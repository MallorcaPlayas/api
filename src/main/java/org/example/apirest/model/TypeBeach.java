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
@Table //(name = "type_beaches")
public class TypeBeach{
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @JsonManagedReference
    @ManyToMany(mappedBy = "types")
    private List<Beach> beaches;

    public TypeBeach(String name) {
        this.name = name;
    }
}
