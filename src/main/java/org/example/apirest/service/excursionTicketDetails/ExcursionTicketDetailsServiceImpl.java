package org.example.apirest.service.excursionTicketDetails;

import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.excursionTicketDetails.CreateExcursionTicketDetailsDto;
import org.example.apirest.dto.excursionTicketDetails.ExcursionTicketDetailsDto;
import org.example.apirest.model.ExcursionTicketDetails;
import org.example.apirest.repository.ExcursionTicketDetailsRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ExcursionTicketDetailsServiceImpl extends GeneralizedServiceImpl<ExcursionTicketDetails, ExcursionTicketDetailsDto, CreateExcursionTicketDetailsDto, ExcursionTicketDetailsRepository> {
    public ExcursionTicketDetailsServiceImpl(ExcursionTicketDetailsRepository repository, DtoConverterImpl<ExcursionTicketDetails, ExcursionTicketDetailsDto, CreateExcursionTicketDetailsDto> dtoConverter) {
        super(repository, dtoConverter, ExcursionTicketDetails.class, ExcursionTicketDetailsDto.class);
    }
}
