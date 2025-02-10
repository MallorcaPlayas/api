package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "excursions_ticket_details")
public class ExcursionTicketDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer price;
    private Integer availableSpaces;

    @ManyToOne
    @JoinColumn(name = "excursion_id")
    private Excursion excursion;

    private LocalTime startTime;
    private LocalTime endTime;

    @OneToMany(mappedBy = "excursionTicketDetails", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Ticket> tickets;
}
