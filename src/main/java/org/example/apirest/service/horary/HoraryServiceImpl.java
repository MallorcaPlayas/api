package org.example.apirest.service.horary;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.function.CreateFunctionDto;
import org.example.apirest.dto.function.FunctionDto;
import org.example.apirest.dto.horary.HoraryDto;
import org.example.apirest.dto.horary.CreateHoraryDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Function;
import org.example.apirest.model.Horary;
import org.example.apirest.repository.FunctionRepository;
import org.example.apirest.repository.HoraryRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoraryServiceImpl extends GeneralizedServiceImpl<Horary, HoraryDto, CreateHoraryDto, HoraryRepository> {
    public HoraryServiceImpl(HoraryRepository repository, DtoConverterImpl<Horary,HoraryDto,CreateHoraryDto> dtoConverter) {
        super(repository, dtoConverter, Horary.class, HoraryDto.class);
    }
}
