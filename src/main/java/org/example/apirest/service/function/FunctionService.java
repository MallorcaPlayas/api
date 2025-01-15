package org.example.apirest.service.function;

import org.example.apirest.dto.function.FunctionDto;
import org.example.apirest.dto.function.CreateFunctionDto;

import java.util.List;

public interface FunctionService {
    List<FunctionDto> findAll();
    FunctionDto findOne(Long id);
    FunctionDto save(CreateFunctionDto function);
    FunctionDto update(Long id, CreateFunctionDto function);
    void delete(Long id);
}
