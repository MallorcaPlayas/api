package org.example.apirest.service.excursion;

import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.excursion.ExcursionDto;
import org.example.apirest.dto.excursion.CreateExcursionDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcursionServiceImpl{

    rotected final R repository;

    @Override
    public List<Dto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), dtoClass);
    }

    @Override
    public Dto findOne(Long id) {
        Entity entity = repository.findById(id).orElseThrow(()-> new NotFoundException(entityClass,id));
        return dtoConverter.convertDto(entity, dtoClass);
    }

    @Override
    public Dto save(CreateDto entity) {
        Entity entityToInsert = dtoConverter.convertToEntityFromCreateDto(entity, entityClass);
        return dtoConverter.convertDto(repository.save(entityToInsert), dtoClass);
    }

    @Override
    public Dto update(Long id, CreateDto createEntity) {
        Entity oldEntity = repository.findById(id).orElseThrow(() -> new NotFoundException(entityClass, id));
        Entity entityToInsert = dtoConverter.convertToEntityFromCreateDto(createEntity, entityClass);

        if (oldEntity == null) {
            return null;
        }

        UtilsClass.updateFields(oldEntity, entityToInsert);

        return dtoConverter.convertDto(repository.save(oldEntity), dtoClass);
    }

    @Override
    public void delete(Long id) {
        Entity entity = repository.findById(id).orElseThrow(()-> new NotFoundException(entityClass,id));
        repository.delete(entity);
    }


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
