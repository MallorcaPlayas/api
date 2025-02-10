package org.example.apirest.dto.excursion;

import lombok.Data;
import org.example.apirest.dto.excursionTicketDetails.ExcursionTicketDetailsDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.user.UserDto;

import java.util.Date;
import java.util.List;

@Data
public class ExcursionDto{
    private Long id;
    private String description;
    private Date creationDate;
    private UserDto user;
    private List<ExcursionTicketDetailsDto> excursionTicketDetails;
    private RouteDto route;
}
