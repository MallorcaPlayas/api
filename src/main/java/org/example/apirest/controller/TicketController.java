package org.example.apirest.controller;

import org.example.apirest.dto.ticket.CreateTicketDto;
import org.example.apirest.dto.ticket.TicketDto;
import org.example.apirest.service.ticket.TicketServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "*")
public class TicketController extends GeneralizedController<TicketDto, CreateTicketDto> {
    public TicketController(TicketServiceImpl service) {
        super(service);
    }
}
