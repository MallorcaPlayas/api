package org.example.apirest.dto.ticket;

import lombok.Data;
import org.example.apirest.dto.user.UserDto;

import java.util.Date;

@Data
public class TicketDto{
    private Long id;
    private Date datePurchase;
    private UserDto userDto;
    private Long excursionTicketDetailsId;
}
