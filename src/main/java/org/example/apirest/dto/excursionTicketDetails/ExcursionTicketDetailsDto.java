package org.example.apirest.dto.excursionTicketDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.excursion.ExcursionDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.model.Excursion;
import org.example.apirest.model.Route;

import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExcursionTicketDetailsDto extends BaseDto {
    private Integer price;
    private Integer availableSpaces;
    private LocalTime startTime;
    private LocalTime endTime;
}
