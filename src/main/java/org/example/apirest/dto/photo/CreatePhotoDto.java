package org.example.apirest.dto.photo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreatePhotoDto{
    private Long id;
    private Long beachId;
    private Long routeId;
    private Long userId;
    private Long complaintId;
    private Long excursionId;
    private boolean isPrivate;
    private MultipartFile file;
    private Long commentId;
}
