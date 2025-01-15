package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table // (name = "beaches")
public class Camera implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String url;

    @ManyToOne
    @JoinColumn(name = "camera_id")
    private Beach beach;
}
