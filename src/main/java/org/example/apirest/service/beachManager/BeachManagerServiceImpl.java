package org.example.apirest.service.beachManager;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.beachHasService.BeachHasServiceDto;
import org.example.apirest.dto.beachManager.BeachManagerDto;
import org.example.apirest.dto.beachManager.CreateBeachManagerDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.repository.*;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeachManagerServiceImpl implements BeachManagerService {

    private final BeachManagerRepository repository;
    private final BeachRepository beachRepository;
    private final UserRepository userRepository;
    private final DtoConverterImpl<BeachManager, BeachManagerDto, CreateBeachManagerDto> serviceDtoConverter;

    @Override
    public List<BeachManagerDto> findAll() {
        return serviceDtoConverter.convertDtoList(repository.findAll(), BeachManagerDto.class);
    }

    @Override
    public BeachManagerDto findOne(Long id) {
        BeachManager entity = repository.findById(id).orElseThrow(()-> new NotFoundException(BeachManager.class,id));
        return serviceDtoConverter.convertDto(entity, BeachManagerDto.class);
    }

    @Override
    public BeachManagerDto save(CreateBeachManagerDto entity) {
        BeachManager entityToInsert = serviceDtoConverter.convertToEntityFromCreateDto(entity, BeachManager.class);
        Beach beach = beachRepository.findById(entity.getBeach_id()).orElseThrow(()-> new NotFoundException(Beach.class,entity.getBeach_id()));
        User user = userRepository.findById(entity.getUser_id()).orElseThrow(()-> new NotFoundException(User.class,entity.getUser_id()));
        entityToInsert.setBeach(beach);
        entityToInsert.setUser(user);
        return serviceDtoConverter.convertDto(repository.save(entityToInsert), BeachManagerDto.class);
    }

    @Override
    public BeachManagerDto update(Long id, CreateBeachManagerDto entity) {
        BeachManager old = repository.findById(id).orElseThrow(()-> new NotFoundException(BeachManager.class,id));
        BeachManager newEntity = serviceDtoConverter.convertToEntityFromCreateDto(entity, BeachManager.class);

        if (entity == null) {
            return null;
        }

        UtilsClass.updateFields(old, newEntity);

        Beach beach = beachRepository.findById(entity.getBeach_id()).orElseThrow(()-> new NotFoundException(Beach.class,entity.getBeach_id()));
        User user = userRepository.findById(entity.getUser_id()).orElseThrow(()-> new NotFoundException(Role.class,entity.getUser_id()));
        old.setBeach(beach);
        old.setUser(user);

        return serviceDtoConverter.convertDto(repository.save(old), BeachManagerDto.class);
    }

    @Override
    public void delete(Long id) {
        BeachManager entity = repository.findById(id).orElseThrow(()-> new NotFoundException(BeachHasService.class,id));
        repository.delete(entity);
    }
}
