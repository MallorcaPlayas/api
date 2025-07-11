package org.example.apirest.dto.location;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.GeoPoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.photo.PhotoDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto{
    private Double longitude;
    private Double latitude;
    private Double elevation;
    private LocalDateTime time;
}
