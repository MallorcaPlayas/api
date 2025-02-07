package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bills")
public class Bill implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String firstSurname;
    private String secondSurname;
    private LocalDateTime birthDate;
    private String email;
    private Double amount;
    private Date amountDate;

    @ManyToOne
    @JoinColumn(name = "bill_type_id")
    private BillType billType;
}
