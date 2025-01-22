package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "horaries")
public class Horary implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime startTime;
    private LocalTime endTime;

    @OneToMany(mappedBy = "horary")
    private List<ExcursionTicketDetails> excursionTicketDetails;

    @OneToMany(mappedBy = "horary")
    private List<BusinessHorary> businessHoraries;
}
