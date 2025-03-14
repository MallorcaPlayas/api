package org.example.apirest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="documents")
public class Document {

    @Id
    private Long id;

    private String bucket;

    private String path;

    @ManyToOne
    @JoinColumn(name = "user_require_role_id")
    @JsonBackReference
    private UserRequireRole userRequireRole;
}
