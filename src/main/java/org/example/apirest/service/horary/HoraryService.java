package org.example.apirest.service.horary;

import org.example.apirest.dto.horary.HoraryDto;
import org.example.apirest.dto.horary.CreateHoraryDto;

import java.util.List;

public interface HoraryService {
    List<HoraryDto> findAll();
    HoraryDto findOne(Long id);
    HoraryDto save(CreateHoraryDto horary);
    HoraryDto update(Long id, CreateHoraryDto horary);
    void delete(Long id);
}
