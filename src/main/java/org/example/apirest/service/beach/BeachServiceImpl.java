package org.example.apirest.service.beach;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Beach;
import org.example.apirest.model.ServiceBeach;
import org.example.apirest.model.TypeBeach;
import org.example.apirest.repository.BeachRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeachServiceImpl implements BeachService {

    private final BeachRepository beachRepository;
    private final DtoConverterImpl<Beach,BeachDto,CreateBeachDto> beachDtoConverter;

    @Override
    public List<BeachDto> findAll() {
        return beachDtoConverter.convertDtoList(beachRepository.findAll(),BeachDto.class);
    }

    @Override
    public BeachDto findOne(Long id) {
        Beach beach = beachRepository.findById(id).orElseThrow(()-> new NotFoundException(Beach.class,id));
        return beachDtoConverter.convertDto(beach,BeachDto.class);
    }

    @Override
    public BeachDto save(CreateBeachDto beach) {
        Beach beachToInsert = beachDtoConverter.convertToEntityFromCreateDto(beach,Beach.class);
        return beachDtoConverter.convertDto(beachRepository.save(beachToInsert),BeachDto.class);
    }

    @Override
    public BeachDto update(Long id, CreateBeachDto beach) {
        Beach oldBeach = beachRepository.findById(id).orElseThrow(()-> new NotFoundException(Beach.class,id));
        Beach beachToInsert = beachDtoConverter.convertToEntityFromCreateDto(beach,Beach.class);

        if (oldBeach == null) {
            return null;
        }
        String newName = beachToInsert.getName();
        String newDescription = beachToInsert.getDescription();
        List<ServiceBeach> newServiceBeachList = beachToInsert.getServices();
        List<TypeBeach> newTypeBeachList = beachToInsert.getTypes();

        if (newName != null){
            oldBeach.setName(newName);
        }

        if (newDescription != null){
            oldBeach.setDescription(newDescription);
        }

        if (newServiceBeachList != null){
            oldBeach.setServices(newServiceBeachList);
        }

        if (newTypeBeachList != null){
            oldBeach.setTypes(newTypeBeachList);
        }


        return beachDtoConverter.convertDto(beachRepository.save(oldBeach),BeachDto.class);
    }

    @Override
    public void delete(Long id) {
        Beach beach = beachRepository.findById(id).orElseThrow(()-> new NotFoundException(Beach.class,id));

        beachRepository.delete(beach);
    }
}
