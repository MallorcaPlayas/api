package org.example.apirest.dto.excursionTicketDetails;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ExcursionTicketDetailsDto{
    private Long id;
    private Integer price;
    private Integer availableSpaces;
    private LocalTime startTime;
    private LocalTime endTime;
}
