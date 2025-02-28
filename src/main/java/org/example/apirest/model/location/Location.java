package org.example.apirest.model.location;

import com.google.cloud.firestore.GeoPoint;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.apirest.model.BaseEntity;
import org.example.apirest.model.beach.Beach;
import org.example.apirest.model.route.Route;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private Long id;
    private GeoPoint location;
    private Double elevation;
    private LocalDateTime time;
}
