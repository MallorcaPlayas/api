package org.example.apirest.dto.excursionTicketDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;

import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateExcursionTicketDetailsDto extends BaseCreateDto {
    private Integer price;
    private Integer availableSpaces;
    private LocalTime startTime;
    private LocalTime endTime;
}
