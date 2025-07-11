package org.example.apirest.dto.complaint;

import lombok.Data;
import org.example.apirest.dto.BaseCreateDto;

@Data
public class CreateComplaintDto {
    private String subject;
    private String message;
    private String status;
    private String date;
    private Long beachId;
    private Long routeId;
    private Long userId;
}
