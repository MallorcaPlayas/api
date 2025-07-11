package org.example.apirest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.apirest.model.beach.Beach;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "beach_has_service")
public class BeachHasService implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "beach_id", nullable = false)
    private Beach beach;

    @ManyToOne
    @JoinColumn(name = "service_beach_id", nullable = false)
    private ServiceBeach serviceBeach;

    //@Column(name= "start_time")
    private LocalTime startTime;

    //@Column(name= "end_time")
    private LocalTime endTime;
}
