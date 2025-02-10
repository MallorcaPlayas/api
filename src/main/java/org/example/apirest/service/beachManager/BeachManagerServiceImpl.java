package org.example.apirest.service.beachManager;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.beachManager.BeachManagerDto;
import org.example.apirest.dto.beachManager.CreateBeachManagerDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.repository.*;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeachManagerServiceImpl{

    private final BeachRepository beachRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

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

    public BeachManagerDto save(CreateBeachManagerDto entity) {
        BeachManager entityToInsert = dtoConverter.convertToEntityFromCreateDto(entity, BeachManager.class);
        Beach beach = beachRepository.findById(entity.getBeach_id()).orElseThrow(()-> new NotFoundException(Beach.class,entity.getBeach_id()));
        User user = userRepository.findById(entity.getUser().getId()).orElseThrow(()-> new NotFoundException(User.class,entity.getUser().getId()));
        entityToInsert.setBeach(beach);
        entityToInsert.setUser(user);
        return dtoConverter.convertDto(repository.save(entityToInsert), BeachManagerDto.class);
    }

    public BeachManagerDto update(Long id, CreateBeachManagerDto entity) {
        BeachManager old = repository.findById(id).orElseThrow(()-> new NotFoundException(BeachManager.class,id));
        BeachManager newEntity = dtoConverter.convertToEntityFromCreateDto(entity, BeachManager.class);

        if (entity == null) {
            return null;
        }

        UtilsClass.updateFields(old, newEntity);

        Beach beach = beachRepository.findById(entity.getBeach_id()).orElseThrow(()-> new NotFoundException(Beach.class,entity.getBeach_id()));
        User user = userRepository.findById(entity.getUser().getId()).orElseThrow(()-> new NotFoundException(Role.class,entity.getUser().getId()));
        old.setBeach(beach);
        old.setUser(user);

        return dtoConverter.convertDto(repository.save(old), BeachManagerDto.class);
    }
}
