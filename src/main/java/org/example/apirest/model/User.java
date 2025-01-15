package org.example.apirest.model;


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

    private String first_name;
    private String last_name;
    private String second_last_name;
    private Date birthday;
    private String password;
    private String urlPhoto;
    private boolean privatePrivacy;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonManagedReference
    private List<Role> roles;
}
