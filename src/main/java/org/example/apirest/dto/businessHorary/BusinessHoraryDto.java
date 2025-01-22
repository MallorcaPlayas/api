package org.example.apirest.dto.businessHorary;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessHoraryDto extends BaseDto {
    private Date date;
    private boolean isOpen;
    private Long businessId;
    private Long horaryId;
}
