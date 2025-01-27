package org.example.apirest.service.excursion;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.beachHasService.BeachHasServiceDto;
import org.example.apirest.dto.beachHasService.CreateBeachHasServiceDto;
import org.example.apirest.dto.beachManager.BeachManagerDto;
import org.example.apirest.dto.beachManager.CreateBeachManagerDto;
import org.example.apirest.dto.camera.CameraDto;
import org.example.apirest.dto.camera.CreateCameraDto;
import org.example.apirest.dto.complaint.ComplaintDto;
import org.example.apirest.dto.complaint.CreateComplaintDto;
import org.example.apirest.dto.excursion.ExcursionDto;
import org.example.apirest.dto.excursion.CreateExcursionDto;
import org.example.apirest.dto.excursionTicketDetails.CreateExcursionTicketDetailsDto;
import org.example.apirest.dto.excursionTicketDetails.ExcursionTicketDetailsDto;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.repository.ComplaintRepository;
import org.example.apirest.repository.ExcursionRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcursionServiceImpl extends GeneralizedServiceImpl<Excursion, ExcursionDto, CreateExcursionDto, ExcursionRepository> {
    private final DtoConverterImpl<User, UserDto, CreateUserDto> dtoUser;
    private final DtoConverterImpl<ExcursionTicketDetails, ExcursionTicketDetailsDto, CreateExcursionTicketDetailsDto> dtoExcursionTicketDetails;
    private final DtoConverterImpl<Route, RouteDto, CreateRouteDto> dtoRoute;
    public ExcursionServiceImpl(ExcursionRepository repository, DtoConverterImpl<Excursion,ExcursionDto,CreateExcursionDto> dtoConverter, DtoConverterImpl<User, UserDto, CreateUserDto> dtoUser, DtoConverterImpl<ExcursionTicketDetails, ExcursionTicketDetailsDto, CreateExcursionTicketDetailsDto> dtoExcursionTicketDetails, DtoConverterImpl<Route, RouteDto, CreateRouteDto> dtoRoute) {
        super(repository, dtoConverter, Excursion.class, ExcursionDto.class);
        this.dtoUser = dtoUser;
        this.dtoExcursionTicketDetails = dtoExcursionTicketDetails;
        this.dtoRoute = dtoRoute;
    }

    @Override
    public ExcursionDto save(CreateExcursionDto entity) {
        Excursion entityToInsert = dtoConverter.convertToEntityFromCreateDto(entity, Excursion.class);

        if (entity.getUser() != null) {
            User user = dtoUser.convertToEntityFromDto(entity.getUser(), User.class);
            entityToInsert.setUser(user);
        }

        if (entity.getRoute() != null) {
            Route route = dtoRoute.convertToEntityFromDto(entity.getRoute(), Route.class);
            entityToInsert.setRoute(route);
        }

        if (entity.getExcursionTicketDetails() != null) {
            List<ExcursionTicketDetails> excursionTicketDetails = dtoExcursionTicketDetails.convertToEntityListFromCreateDto(entity.getExcursionTicketDetails(), ExcursionTicketDetails.class);
            for (ExcursionTicketDetails etd : excursionTicketDetails) {
                etd.setExcursion(entityToInsert);
            }
            entityToInsert.setExcursionTicketDetails(excursionTicketDetails);
        }

        return dtoConverter.convertDto(repository.save(entityToInsert), ExcursionDto.class);

    }

    @Override
    public ExcursionDto update(Long id, CreateExcursionDto entity) {
        Excursion old = repository.findById(id).orElseThrow(() -> new NotFoundException(Excursion.class, id));
        Excursion newEntity = dtoConverter.convertToEntityFromCreateDto(entity, Excursion.class);

        UtilsClass.updateFields(old, newEntity);

        if (entity.getExcursionTicketDetails() != null) {
            old.getExcursionTicketDetails().clear();
            List<ExcursionTicketDetails> excursionTicketDetails = dtoExcursionTicketDetails.convertToEntityListFromCreateDto(entity.getExcursionTicketDetails(), ExcursionTicketDetails.class);
            for (ExcursionTicketDetails etd : excursionTicketDetails) {
                etd.setExcursion(old);
            }
            old.getExcursionTicketDetails().addAll(excursionTicketDetails);
        }

        return dtoConverter.convertDto(repository.save(old), ExcursionDto.class);
    }
}
