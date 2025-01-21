package org.example.apirest.dto.ticket;

import lombok.Data;

import java.util.Date;

@Data
public class CreateTicketDto {
    private Date datePurchase;
    private Long userId;
    private Long excursionTicketDetailsId;
}
