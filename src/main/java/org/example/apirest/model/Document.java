package org.example.apirest.model;

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
    private UserRequireRole userRequireRole;
}
