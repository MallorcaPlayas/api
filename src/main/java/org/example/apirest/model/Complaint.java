package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "complaints")
public class Complaint implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String subject;
    private String message;
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
