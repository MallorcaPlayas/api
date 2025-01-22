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
@Table(name = "routes")
public class Route implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean isPrivate;
    private double distance;
    private double duration;
    private double elevation;


    @OneToMany(mappedBy = "route")
    private List<Complaint> routeComplaints;

    @OneToMany(mappedBy = "route")
    private List<Comment> routeComments;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "route")
    private List<ExcursionTicketDetails> excursionTicketDetails;

    @OneToMany(mappedBy = "route")
    private List<Location> locations;
}
