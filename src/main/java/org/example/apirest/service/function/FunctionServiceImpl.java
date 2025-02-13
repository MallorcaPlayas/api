package org.example.apirest.service.function;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.function.FunctionDto;
import org.example.apirest.dto.function.CreateFunctionDto;
import org.example.apirest.model.Function;
import org.example.apirest.repository.FunctionRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FunctionServiceImpl extends GeneralizedServiceImpl<Function, FunctionDto, CreateFunctionDto, FunctionRepository> {
    public FunctionServiceImpl(FunctionRepository repository, DtoConverterGeneralizedImpl<Function,FunctionDto,CreateFunctionDto> dtoConverter) {
        super(repository, dtoConverter, Function.class, FunctionDto.class);
    }
}
