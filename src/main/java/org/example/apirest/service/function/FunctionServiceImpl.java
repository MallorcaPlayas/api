package org.example.apirest.service.function;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.excursion.CreateExcursionDto;
import org.example.apirest.dto.excursion.ExcursionDto;
import org.example.apirest.dto.function.FunctionDto;
import org.example.apirest.dto.function.CreateFunctionDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Excursion;
import org.example.apirest.model.Function;
import org.example.apirest.repository.ExcursionRepository;
import org.example.apirest.repository.FunctionRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionServiceImpl extends GeneralizedServiceImpl<Function, FunctionDto, CreateFunctionDto, FunctionRepository> {
    public FunctionServiceImpl(FunctionRepository repository, DtoConverterImpl<Function,FunctionDto,CreateFunctionDto> dtoConverter) {
        super(repository, dtoConverter, Function.class, FunctionDto.class);
    }
}
