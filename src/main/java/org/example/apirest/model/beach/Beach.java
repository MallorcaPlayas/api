package org.example.apirest.model.beach;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.example.apirest.model.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "beaches")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Beach implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "beach", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<BeachHasService> beachHasServiceBeach;

    @ManyToMany
    @JoinTable(
            name = "beach_type", // Name of the join table
            joinColumns = @JoinColumn(name = "beach_id"), // Foreign key for Beach
            inverseJoinColumns = @JoinColumn(name = "type_id") // Foreign key for TypeBeach
    )
    @JsonManagedReference
    private List<TypeBeach> types;

    @OneToMany(mappedBy = "beach", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Camera> cameras;

    @OneToMany(mappedBy = "beach", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Complaint> beachComplaints;

    @OneToMany(mappedBy = "beach", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Comment> beachComments;

    @OneToMany(mappedBy = "beach", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<BeachManager> usersInCharge;

    @Override
    public String toString() {
        return "Beach{id=" + id + ", name='" + name + "', description='" + description + "'}";
    }

}
