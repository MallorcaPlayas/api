package org.example.apirest.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.example.apirest.model.beach.Beach;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "beach_has_service")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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


    // ❗ Definimos `toString()` manualmente para evitar recursión infinita
    @Override
    public String toString() {
        return "BeachHasService{id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + "}";
    }
}
