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
@Table(name = "organizations")
public class Organization implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String documentationUrl;
    private String contactNumber;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<User> users;
}
