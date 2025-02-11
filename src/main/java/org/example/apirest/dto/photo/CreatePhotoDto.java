package org.example.apirest.dto.photo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreatePhotoDto{
    private Long id;
    private Long beachId;
    private Long routeId;
    private Long userId;
    private Long excursionId;
    private Boolean isPrivate = false;
    private MultipartFile file;
}
