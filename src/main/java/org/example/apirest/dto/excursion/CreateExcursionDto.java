package org.example.apirest.dto.excursion;

import lombok.Data;
import org.example.apirest.dto.excursionTicketDetails.CreateExcursionTicketDetailsDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.user.UserDto;

import java.util.Date;
import java.util.List;

@Data
public class CreateExcursionDto{
    private String description;
    private Date creationDate;
    private UserDto user;
    private List<CreateExcursionTicketDetailsDto> excursionTicketDetails;
    private RouteDto route;
}
