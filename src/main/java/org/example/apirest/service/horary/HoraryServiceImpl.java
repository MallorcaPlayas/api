package org.example.apirest.service.horary;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.horary.HoraryDto;
import org.example.apirest.dto.horary.CreateHoraryDto;
import org.example.apirest.model.Horary;
import org.example.apirest.repository.HoraryRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class HoraryServiceImpl extends GeneralizedServiceImpl<Horary, HoraryDto, CreateHoraryDto, HoraryRepository> {
    public HoraryServiceImpl(HoraryRepository repository, DtoConverterGeneralizedImpl<Horary,HoraryDto,CreateHoraryDto> dtoConverter) {
        super(repository, dtoConverter, Horary.class, HoraryDto.class);
    }
}
