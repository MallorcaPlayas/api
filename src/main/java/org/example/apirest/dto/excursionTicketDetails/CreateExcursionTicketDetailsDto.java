package org.example.apirest.dto.excursionTicketDetails;

import lombok.Data;

import java.time.LocalTime;

@Data
public class CreateExcursionTicketDetailsDto{
    private String name;
    private Integer price;
    private Integer availableSpaces;
    private LocalTime startTime;
    private LocalTime endTime;
}
