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
@Table(name = "businesses_horary")
public class BusinessHorary implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    private boolean isOpen;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @ManyToOne
    @JoinColumn(name = "horary_id")
    private Horary horary;
}
