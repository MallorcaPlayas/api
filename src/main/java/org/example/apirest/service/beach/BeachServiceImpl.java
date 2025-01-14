package org.example.apirest.service.beach;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.BeachDtoConverter;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.model.Beach;
import org.example.apirest.repository.BeachRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeachServiceImpl implements BeachService {

    private final BeachRepository beachRepository;
    private final BeachDtoConverter beachDtoConverter;

    @Override
    public List<BeachDto> findAll() {
        return beachDtoConverter.convertDtoList(beachRepository.findAll());
    }

    @Override
    public BeachDto findOne(Long id) {
        Beach beach = beachRepository.findById(id).orElse(null);
        return beachDtoConverter.convertDto(beach);
    }

    @Override
    public BeachDto save(CreateBeachDto beach) {
        Beach beachToInsert = beachDtoConverter.convertToCreateBeachEntity(beach);
        return beachDtoConverter.convertDto(beachRepository.save(beachToInsert));
    }

    @Override
    public BeachDto update(Long id, CreateBeachDto beach) {
        Beach oldBeach = beachRepository.findById(id).orElse(null);
        Beach beachToInsert = beachDtoConverter.convertToCreateBeachEntity(beach);

        if (oldBeach == null) {
            return null;
        }

        oldBeach.setName(beachToInsert.getName());
        oldBeach.setDescription(beachToInsert.getDescription());
        oldBeach.setServiceBeaches(beachToInsert.getServiceBeaches());
        oldBeach.setTypes(beachToInsert.getTypes());


        return beachDtoConverter.convertDto(beachRepository.save(beachToInsert));
    }

    @Override
    public void delete(Long id) {
        Beach beach = beachRepository.findById(id).orElse(null);

        beachRepository.delete(beach);
    }
}
