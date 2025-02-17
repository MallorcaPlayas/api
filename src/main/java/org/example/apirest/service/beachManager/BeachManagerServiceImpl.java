package org.example.apirest.service.beachManager;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.beachManager.BeachManagerDto;
import org.example.apirest.dto.beachManager.CreateBeachManagerDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.model.beach.Beach;
import org.example.apirest.repository.*;
import org.example.apirest.repository.beach.BeachManagerRepository;
import org.example.apirest.repository.beach.BeachRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.Utils;
import org.springframework.stereotype.Service;

@Service
public class BeachManagerServiceImpl extends GeneralizedServiceImpl<BeachManager, BeachManagerDto, CreateBeachManagerDto, BeachManagerRepository> {

    private final BeachRepository beachRepository;
    private final UserRepository userRepository;

    public BeachManagerServiceImpl(BeachManagerRepository repository, DtoConverterGeneralizedImpl<BeachManager,BeachManagerDto,CreateBeachManagerDto> dtoConverter, BeachRepository beachRepository, UserRepository userRepository) {
        super(repository, dtoConverter, BeachManager.class, BeachManagerDto.class);
        this.beachRepository = beachRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BeachManagerDto save(CreateBeachManagerDto entity) {
        BeachManager entityToInsert = dtoConverter.convertToEntityFromCreateDto(entity, BeachManager.class);
        Beach beach = beachRepository.findById(entity.getBeach_id()).orElseThrow(()-> new NotFoundException(Beach.class,entity.getBeach_id()));
        User user = userRepository.findById(entity.getUser().getId()).orElseThrow(()-> new NotFoundException(User.class,entity.getUser().getId()));
        entityToInsert.setBeach(beach);
        entityToInsert.setUser(user);
        return dtoConverter.convertDto(repository.save(entityToInsert), BeachManagerDto.class);
    }

    @Override
    public BeachManagerDto update(Long id, CreateBeachManagerDto entity) {
        BeachManager old = repository.findById(id).orElseThrow(()-> new NotFoundException(BeachManager.class,id));
        BeachManager newEntity = dtoConverter.convertToEntityFromCreateDto(entity, BeachManager.class);

        if (entity == null) {
            return null;
        }

        Utils.updateFields(old, newEntity);

        Beach beach = beachRepository.findById(entity.getBeach_id()).orElseThrow(()-> new NotFoundException(Beach.class,entity.getBeach_id()));
        User user = userRepository.findById(entity.getUser().getId()).orElseThrow(()-> new NotFoundException(Role.class,entity.getUser().getId()));
        old.setBeach(beach);
        old.setUser(user);

        return dtoConverter.convertDto(repository.save(old), BeachManagerDto.class);
    }
}
