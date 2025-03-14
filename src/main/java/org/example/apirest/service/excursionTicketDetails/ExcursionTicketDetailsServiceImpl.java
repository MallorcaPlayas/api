package org.example.apirest.service.excursionTicketDetails;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.excursion.CreateExcursionDto;
import org.example.apirest.dto.excursion.ExcursionDto;
import org.example.apirest.dto.excursionTicketDetails.CreateExcursionTicketDetailsDto;
import org.example.apirest.dto.excursionTicketDetails.ExcursionTicketDetailsDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.model.*;
import org.example.apirest.model.route.Route;
import org.example.apirest.repository.ExcursionTicketDetailsRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ExcursionTicketDetailsServiceImpl extends GeneralizedServiceImpl<ExcursionTicketDetails, ExcursionTicketDetailsDto, CreateExcursionTicketDetailsDto, ExcursionTicketDetailsRepository> {

    public ExcursionTicketDetailsServiceImpl(ExcursionTicketDetailsRepository repository, DtoConverterGeneralizedImpl<ExcursionTicketDetails, ExcursionTicketDetailsDto, CreateExcursionTicketDetailsDto> dtoConverter, DtoConverterGeneralizedImpl<Excursion, ExcursionDto, CreateExcursionDto> dtoExcursion, DtoConverterGeneralizedImpl<Route, RouteDto, CreateRouteDto> dtoRoute) {
        super(repository, dtoConverter, ExcursionTicketDetails.class, ExcursionTicketDetailsDto.class);
    }
}
