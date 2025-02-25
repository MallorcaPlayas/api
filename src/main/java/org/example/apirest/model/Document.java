package org.example.apirest.model;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {

    @Id
    private Long id;

    private String bucket;

    private String path;

    @ManyToOne
    private UserRequireRole userRequireRole;
}
