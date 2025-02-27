package org.example.apirest.model.location;

import com.google.cloud.firestore.GeoPoint;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.apirest.model.beach.Beach;
import org.example.apirest.model.route.Route;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationFirestore {

    private Long id;
    private GeoPoint location;
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @OneToOne
    @JoinColumn(name = "beach_id")
    private Beach beach;
}
