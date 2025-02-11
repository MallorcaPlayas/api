package org.example.apirest.service.excursionTicketDetails;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.excursionTicketDetails.ExcursionTicketDetailsDto;
import org.example.apirest.dto.excursionTicketDetails.CreateExcursionTicketDetailsDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.ExcursionTicketDetails;
import org.example.apirest.repository.ExcursionTicketDetailsRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcursionTicketDetailsServiceImpl implements DtoConverter<ExcursionTicketDetails, ExcursionTicketDetailsDto, CreateExcursionTicketDetailsDto> {

    private final ExcursionTicketDetailsRepository repository;
    private final ModelMapper mapper;

    public List<ExcursionTicketDetailsDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public ExcursionTicketDetailsDto findOne(Long id) {
        ExcursionTicketDetails excursionTicketDetails = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExcursionTicketDetails.class, id));
        return this.toDto(excursionTicketDetails);
    }

    public ExcursionTicketDetailsDto save(CreateExcursionTicketDetailsDto createExcursionTicketDetailsDto) {
        ExcursionTicketDetails excursionTicketDetails = fromDto(createExcursionTicketDetailsDto);
        ExcursionTicketDetails savedExcursionTicketDetails = repository.save(excursionTicketDetails);
        return toDto(savedExcursionTicketDetails);
    }

    public ExcursionTicketDetailsDto update(Long id, CreateExcursionTicketDetailsDto createExcursionTicketDetailsDto) {
        ExcursionTicketDetails oldExcursionTicketDetails = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExcursionTicketDetails.class, id));
        ExcursionTicketDetails excursionTicketDetailsToUpdate = fromDto(createExcursionTicketDetailsDto);

        UtilsClass.updateFields(oldExcursionTicketDetails, excursionTicketDetailsToUpdate);

        ExcursionTicketDetails savedExcursionTicketDetails = repository.save(oldExcursionTicketDetails);
        return toDto(savedExcursionTicketDetails);
    }

    public void delete(Long id) {
        ExcursionTicketDetails excursionTicketDetails = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExcursionTicketDetails.class, id));
        repository.delete(excursionTicketDetails);
    }

    @Override
    public ExcursionTicketDetailsDto toDto(ExcursionTicketDetails excursionTicketDetails) {
        return mapper.map(excursionTicketDetails, ExcursionTicketDetailsDto.class);
    }

    @Override
    public List<ExcursionTicketDetailsDto> toDtoList(List<ExcursionTicketDetails> excursionTicketDetailsList) {
        return excursionTicketDetailsList.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public ExcursionTicketDetails fromDto(CreateExcursionTicketDetailsDto createExcursionTicketDetailsDto) {
        return mapper.map(createExcursionTicketDetailsDto, ExcursionTicketDetails.class);
    }

    @Override
    public List<ExcursionTicketDetails> fromDtoList(List<CreateExcursionTicketDetailsDto> createExcursionTicketDetailsDtos) {
        return createExcursionTicketDetailsDtos.stream()
                .map(this::fromDto)
                .toList();
    }
}
