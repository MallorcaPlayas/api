package org.example.apirest.dto.excursion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.excursionTicketDetails.CreateExcursionTicketDetailsDto;
import org.example.apirest.dto.excursionTicketDetails.ExcursionTicketDetailsDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.user.UserDto;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExcursionDto extends BaseDto {
    private String description;
    private Date creationDate;
    private UserDto user;
    private List<ExcursionTicketDetailsDto> excursionTicketDetails;
    private RouteDto route;
}
