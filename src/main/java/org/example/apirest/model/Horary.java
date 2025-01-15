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
@Table(name = "horaries")
public class Horary implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Date startTime;
    private Date endTime;
}
