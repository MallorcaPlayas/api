package org.example.apirest.dto.location;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.GeoPoint;
import lombok.Data;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateLocationDto{
    private GeoPoint point;
    private Double elevation;
    private Timestamp time;
}
