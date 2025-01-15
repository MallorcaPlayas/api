package org.example.apirest.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table // (name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;

    private String first_name;
    private String last_name;
    private String second_last_name;
    private Date birthday;
    private String password;
    private String urlPhoto;
    private boolean privatePrivacy;
}
