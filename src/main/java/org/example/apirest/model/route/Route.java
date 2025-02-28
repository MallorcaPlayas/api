package org.example.apirest.model.route;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.apirest.model.*;
import org.example.apirest.model.location.Location;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "routes")
public class Route implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Boolean isPrivate;
    private Double distance;
    private Double duration;
    private Double elevationAsc;
    private Double elevationDesc;
    private List<Location> locations;


    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Complaint> routeComplaints;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Comment> routeComments;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Excursion> excursions;


    @OneToMany(mappedBy = "route")
    private List<Photo> photos;
}
