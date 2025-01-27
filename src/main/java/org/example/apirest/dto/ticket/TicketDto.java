package org.example.apirest.dto.ticket;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.dto.user.UserDto;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class TicketDto extends BaseDto {
    private Date datePurchase;
    private UserDto userDto;
    private Long excursionTicketDetailsId;
}
