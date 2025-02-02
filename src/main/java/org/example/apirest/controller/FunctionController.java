package org.example.apirest.controller;

import org.example.apirest.dto.function.FunctionDto;
import org.example.apirest.dto.function.CreateFunctionDto;
import org.example.apirest.service.function.FunctionServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/functions")
@CrossOrigin(origins = "*")
public class FunctionController extends GeneralizedController<FunctionDto, CreateFunctionDto> {
    public FunctionController(FunctionServiceImpl service) {
        super(service);
    }
}
