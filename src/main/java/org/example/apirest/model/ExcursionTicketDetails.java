package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "excursions_ticket_details")
public class ExcursionTicketDetails implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Integer price;
    private Integer availableSpaces;

    @ManyToOne
    @JoinColumn(name = "excursion_id")
    private Excursion excursion;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "horary_id")
    private Horary horary;


}
