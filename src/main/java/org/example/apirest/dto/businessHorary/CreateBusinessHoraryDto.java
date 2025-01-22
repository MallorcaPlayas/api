package org.example.apirest.dto.businessHorary;

import lombok.Data;

import java.util.Date;

@Data
public class CreateBusinessHoraryDto {
    private Date date;
    private boolean isOpen;
    private Long businessId;
    private Long horaryId;
}
