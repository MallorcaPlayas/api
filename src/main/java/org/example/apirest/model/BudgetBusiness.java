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
@Table(name = "budget_business")
public class BudgetBusiness implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Budget budget;

    @OneToOne
    private Business business;

    private boolean paid;

    private Date proposalDate;

    private Date paidDate;

    private Date endDate;


}
