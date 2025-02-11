package org.example.apirest.dto.photo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreatePhotoDto{
    private Long beachId;
    private Long routeId;
    private Long userId;
    private Long commentId;
    private MultipartFile file;
    private Boolean isPrivate;
}
