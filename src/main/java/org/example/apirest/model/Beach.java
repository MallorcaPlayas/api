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
@Table  (name = "beaches")
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

    @OneToOne(mappedBy = "beach" , cascade = CascadeType.ALL , orphanRemoval = true)
    private Location location;
}
