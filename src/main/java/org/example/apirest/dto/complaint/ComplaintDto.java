package org.example.apirest.dto.complaint;

import lombok.Data;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.beach.BeachDto;

@Data
public class ComplaintDto {
    private Long id;
    private String message;
    private String status;
    private String date;
    private Long beachId;
}
