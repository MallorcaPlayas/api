package org.example.apirest.dto.complaint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class ComplaintDto extends BaseDto {
    private String message;
    private String status;
    private String date;
    private Long beachId;
    private Long routeId;
}
