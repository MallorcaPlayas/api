package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
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
    private boolean isPrivate;
    private double distance;
    private double duration;
    @Column(name = "elevation_asc")
    private double elevationAsc;
    @Column(name = "elevation_desc")
    private double elevationDesc;


    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Complaint> routeComplaints;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Comment> routeComments;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Excursion> excursions;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Location> locations;
}
