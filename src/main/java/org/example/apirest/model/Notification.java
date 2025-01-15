package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String subject;
    private String description;
    private boolean read;
}
