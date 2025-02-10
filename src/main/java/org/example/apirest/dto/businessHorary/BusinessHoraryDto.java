package org.example.apirest.dto.businessHorary;

import lombok.Data;

import java.util.Date;

@Data
public class BusinessHoraryDto{
    private Long id;
    private Date date;
    private boolean isOpen;
    private Long businessId;
    private Long horaryId;
}
