package org.example.apirest.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "users_project")
public class User implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    private String userName;
    private String firstSurname;
    private String secondSurname;
    private String email;
    private Date birthday;
    private String password;
    private String urlPhoto;
    private boolean privatePrivacy;
    private boolean state;

    @OneToMany(mappedBy = "user")
    private List<UserHasRole> userHasRoles;

    @OneToMany(mappedBy = "user")
    private List<UserRequireRole> userRequireRoles;

    @ManyToMany(mappedBy = "usersInCharge")
    private List<Beach> beaches;

    @OneToMany(mappedBy = "user")
    private List<Route> routes;

    @OneToMany(mappedBy = "user")
    private List<Excursion> excursions;

    @ManyToOne
    private Organization organization;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Complaint> complaints;
}
