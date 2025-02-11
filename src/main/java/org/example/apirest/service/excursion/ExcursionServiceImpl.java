package org.example.apirest.service.excursion;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.excursion.ExcursionDto;
import org.example.apirest.dto.excursion.CreateExcursionDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Excursion;
import org.example.apirest.model.User;
import org.example.apirest.model.Route;
import org.example.apirest.model.ExcursionTicketDetails;
import org.example.apirest.repository.ExcursionRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcursionServiceImpl implements DtoConverter<Excursion,ExcursionDto,CreateExcursionDto> {

    private final ExcursionRepository repository;
    private final ModelMapper mapper;

    public List<ExcursionDto> findAll() {
        List<Excursion> excursions = repository.findAll();
        return this.toDtoList(excursions);
    }

    public ExcursionDto findOne(Long id) {
        Excursion excursion = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Excursion.class, id));
        return toDto(excursion);
    }

    public ExcursionDto save(CreateExcursionDto createDto) {
        // Mapea el DTO a la entidad Excursion
        Excursion excursion = this.fromDto(createDto);

//        // Procesa la asociación con User si se proporciona
//        if (createDto.getUser() != null) {
//            User user = mapper.map(createDto.getUser(), User.class);
//            excursion.setUser(user);
//        }
//
//        // Procesa la asociación con Route si se proporciona
//        if (createDto.getRoute() != null) {
//            Route route = mapper.map(createDto.getRoute(), Route.class);
//            excursion.setRoute(route);
//        }
//
//        // Procesa la lista de ExcursionTicketDetails si se proporciona
//        if (createDto.getExcursionTicketDetails() != null) {
//            List<ExcursionTicketDetails> ticketDetails = createDto.getExcursionTicketDetails().stream()
//                    .map(dto -> {
//                        ExcursionTicketDetails etd = mapper.map(dto, ExcursionTicketDetails.class);
//                        etd.setExcursion(excursion);
//                        return etd;
//                    })
//                    .toList();
//            excursion.setExcursionTicketDetails(ticketDetails);
//        }

        Excursion savedExcursion = repository.save(excursion);
        return this.toDto(savedExcursion);
    }


    public ExcursionDto update(Long id, CreateExcursionDto createDto) {
        Excursion existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Excursion.class, id));

        Excursion updatedData = this.fromDto(createDto);

        // Actualiza los campos modificables de la entidad existente
        UtilsClass.updateFields(existing, updatedData);

        // Si se proporcionan ExcursionTicketDetails, actualiza la lista
//        if (createDto.getExcursionTicketDetails() != null) {
//            existing.getExcursionTicketDetails().clear();
//            List<ExcursionTicketDetails> ticketDetails = createDto.getExcursionTicketDetails().stream()
//                    .map(dto -> {
//                        ExcursionTicketDetails etd = mapper.map(dto, ExcursionTicketDetails.class);
//                        etd.setExcursion(existing);
//                        return etd;
//                    })
//                    .toList();
//            existing.getExcursionTicketDetails().addAll(ticketDetails);
//        }

        Excursion savedExcursion = repository.save(existing);
        return toDto(savedExcursion);
    }

    public void delete(Long id) {
        Excursion excursion = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Excursion.class, id));
        repository.delete(excursion);
    }

    @Override
    public ExcursionDto toDto(Excursion excursion) {
        return mapper.map(excursion, ExcursionDto.class);
    }

    @Override
    public List<ExcursionDto> toDtoList(List<Excursion> excursions) {
        return excursions.stream().map(this::toDto).toList();
    }

    @Override
    public Excursion fromDto(CreateExcursionDto createExcursionDto) {
        return mapper.map(createExcursionDto, Excursion.class);
    }

    @Override
    public List<Excursion> fromDtoList(List<CreateExcursionDto> createExcursionDtos) {
        return createExcursionDtos.stream().map(this::fromDto).toList();
    }
}
