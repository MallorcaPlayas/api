package org.example.apirest.service.function;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.function.FunctionDto;
import org.example.apirest.dto.function.CreateFunctionDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Function;
import org.example.apirest.repository.FunctionRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FunctionServiceImpl implements FunctionService {

    private final FunctionRepository repository;
    private final DtoConverterImpl<Function, FunctionDto, CreateFunctionDto> dtoConverter;

    @Override
    public List<FunctionDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), FunctionDto.class);
    }

    @Override
    public FunctionDto findOne(Long id) {
        Function function = repository.findById(id).orElseThrow(() -> new NotFoundException(Function.class, id));
        return dtoConverter.convertDto(function, FunctionDto.class);
    }

    @Override
    public FunctionDto save(CreateFunctionDto function) {
        Function functionToInsert = dtoConverter.convertToEntityFromCreateDto(function, Function.class);
        return dtoConverter.convertDto(repository.save(functionToInsert), FunctionDto.class);
    }

    @Override
    public FunctionDto update(Long id, CreateFunctionDto function) {
        Function oldFunction = repository.findById(id).orElseThrow(() -> new NotFoundException(Function.class, id));
        Function functionToInsert = dtoConverter.convertToEntityFromCreateDto(function, Function.class);

        if (oldFunction == null) {
            return null;
        }

        UtilsClass.updateFields(oldFunction, functionToInsert);

        return dtoConverter.convertDto(repository.save(oldFunction), FunctionDto.class);
    }

    @Override
    public void delete(Long id) {
        Function function = repository.findById(id).orElseThrow(() -> new NotFoundException(Function.class, id));
        repository.delete(function);
    }
}
