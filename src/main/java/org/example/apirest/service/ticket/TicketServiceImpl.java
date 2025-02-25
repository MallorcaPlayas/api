package org.example.apirest.service.ticket;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.ticket.CreateTicketDto;
import org.example.apirest.dto.ticket.TicketDto;
import org.example.apirest.model.Ticket;
import org.example.apirest.repository.TicketRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl extends GeneralizedServiceImpl<Ticket, TicketDto, CreateTicketDto, TicketRepository> {
    public TicketServiceImpl(TicketRepository repository, DtoConverterGeneralizedImpl<Ticket,TicketDto,CreateTicketDto> dtoConverter) {
        super(repository, dtoConverter, Ticket.class, TicketDto.class);
    }
}
