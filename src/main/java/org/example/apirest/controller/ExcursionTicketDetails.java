package org.example.apirest.controller;

import org.example.apirest.dto.excursion.CreateExcursionDto;
import org.example.apirest.dto.excursion.ExcursionDto;
import org.example.apirest.dto.excursionTicketDetails.CreateExcursionTicketDetailsDto;
import org.example.apirest.dto.excursionTicketDetails.ExcursionTicketDetailsDto;
import org.example.apirest.service.excursionTicketDetails.ExcursionTicketDetailsServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/excursions-ticket-details")
@CrossOrigin(origins = "*")
public class ExcursionTicketDetails extends GeneralizedController<ExcursionTicketDetailsDto, CreateExcursionTicketDetailsDto> {
    public ExcursionTicketDetails(ExcursionTicketDetailsServiceImpl service) {
        super(service);
    }
}
