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
@Table(name = "tickets")
public class Ticket implements BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Date datePurchase;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "excursion_ticket_details_id")
    private ExcursionTicketDetails excursionTicketDetails;
}
