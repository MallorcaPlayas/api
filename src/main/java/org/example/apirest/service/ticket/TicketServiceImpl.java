package org.example.apirest.service.ticket;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.ticket.TicketDto;
import org.example.apirest.dto.ticket.CreateTicketDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Ticket;
import org.example.apirest.repository.TicketRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements DtoConverter<Ticket, TicketDto, CreateTicketDto> {

    private final TicketRepository repository;
    private final ModelMapper mapper;

    public List<TicketDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public TicketDto findOne(Long id) {
        Ticket ticket = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Ticket.class, id));
        return this.toDto(ticket);
    }

    public TicketDto save(CreateTicketDto createTicketDto) {
        Ticket ticket = fromDto(createTicketDto);
        Ticket savedTicket = repository.save(ticket);
        return toDto(savedTicket);
    }

    public TicketDto update(Long id, CreateTicketDto createTicketDto) {
        Ticket oldTicket = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Ticket.class, id));
        Ticket ticketToUpdate = fromDto(createTicketDto);

        UtilsClass.updateFields(oldTicket, ticketToUpdate);

        Ticket savedTicket = repository.save(oldTicket);
        return toDto(savedTicket);
    }

    public void delete(Long id) {
        Ticket ticket = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Ticket.class, id));
        repository.delete(ticket);
    }

    @Override
    public TicketDto toDto(Ticket ticket) {
        return mapper.map(ticket, TicketDto.class);
    }

    @Override
    public List<TicketDto> toDtoList(List<Ticket> tickets) {
        return tickets.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public Ticket fromDto(CreateTicketDto createTicketDto) {
        return mapper.map(createTicketDto, Ticket.class);
    }

    @Override
    public List<Ticket> fromDtoList(List<CreateTicketDto> createTicketDtos) {
        return createTicketDtos.stream()
                .map(this::fromDto)
                .toList();
    }
}