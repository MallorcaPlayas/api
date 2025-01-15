package org.example.apirest.dto.complaint;

import lombok.Data;
import org.example.apirest.dto.BaseDto;

@Data
public class ComplaintDto {
    private Long id;
    private String message;
    private String status;
    private String date;
}
