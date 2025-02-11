package org.example.apirest.service.function;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.function.FunctionDto;
import org.example.apirest.dto.function.CreateFunctionDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Function;
import org.example.apirest.repository.FunctionRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FunctionServiceImpl implements DtoConverter<Function, FunctionDto, CreateFunctionDto> {

    private final FunctionRepository repository;
    private final ModelMapper mapper;
    private final ModelMapper modelMapper;

    public List<FunctionDto> findAll() {
        List<Function> functions = repository.findAll();
        return toDtoList(functions);
    }


    public FunctionDto findOne(Long id) {
        Function function = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Function.class, id));
        return toDto(function);
    }

    public FunctionDto save(CreateFunctionDto createFunctionDto) {
        Function function = fromDto(createFunctionDto);
        Function savedFunction = repository.save(function);
        return toDto(savedFunction);
    }

    public FunctionDto update(Long id, CreateFunctionDto createFunctionDto) {
        Function existingFunction = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Function.class, id));
        Function updatedData = fromDto(createFunctionDto);

        // Actualiza los campos modificables de la entidad existente.
        UtilsClass.updateFields(existingFunction, updatedData);

        Function savedFunction = repository.save(existingFunction);
        return toDto(savedFunction);
    }

    public void delete(Long id) {
        Function function = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Function.class, id));
        repository.delete(function);
    }


    @Override
    public FunctionDto toDto(Function function) {
        return mapper.map(function, FunctionDto.class);
    }

    @Override
    public List<FunctionDto> toDtoList(List<Function> functions) {
        return functions.stream().map(this::toDto).toList();
    }

    @Override
    public Function fromDto(CreateFunctionDto createFunctionDto) {
        return mapper.map(createFunctionDto, Function.class);
    }

    @Override
    public List<Function> fromDtoList(List<CreateFunctionDto> createFunctionDtos) {
        return createFunctionDtos.stream().map(this::fromDto).toList();
    }
}
