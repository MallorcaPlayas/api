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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "user_name") // Ajuste al nombre de la columna en la base de datos
    private String userName;

    @Column(name = "first_surname") // Ajuste al nombre de la columna en la base de datos
    private String firstSurname;

    @Column(name = "second_surname") // Ajuste al nombre de la columna en la base de datos
    private String secondSurname;
    private String email;
    private Date birthday;
    private String password;

    @Column(name = "profile_picture") // Ajuste al nombre de la columna en la base de datos
    private String profilePicture; // Cambiado de `urlPhoto` a `profilePicture` para coincidir con la base de datos

    @Column(name = "private_privacy") // Ajuste al nombre de la columna en la base de datos
    private boolean privatePrivacy;
    private boolean state;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = true)
    private Organization organization;

    @Column(name = "email_verified_at", nullable = true)
    private Date emailVerifiedAt;

    @Column(name = "remember_token", nullable = true)
    private String rememberToken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<UserHasRole> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<UserRequireRole> userRequireRoles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<BeachManager> beaches;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Route> routes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Excursion> excursions;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Complaint> complaints;

//    @OneToOne
//    private Photo photo;
}
