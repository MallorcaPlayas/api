package org.example.apirest.dto.complaint;

import lombok.Data;

@Data
public class ComplaintDto {
    private Long id;
    private String message;
    private String status;
    private String date;
    private Long beachId;
}
