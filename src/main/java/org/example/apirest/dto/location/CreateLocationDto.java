package org.example.apirest.dto.location;

import lombok.Data;
import org.example.apirest.dto.photo.CreatePhotoDto;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateLocationDto{
    private Double longitude;
    private Double latitude;
    private Double elevation;
    private LocalDateTime time;
    private Long beachId;
}
