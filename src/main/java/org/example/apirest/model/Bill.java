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
@Table(name = "bills")
public class Bill implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String userName;
    private String firstSurname;
    private String secondSurname;
    private Date birthDate;
    private String email;
    private Double amount;
    private Date amountDate;
}
