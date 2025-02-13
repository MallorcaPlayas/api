package org.example.apirest.service.roleHasFunction;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.role_has_function.CreateRoleHasFunctionDto;
import org.example.apirest.dto.role_has_function.RoleHasFunctionDto;
import org.example.apirest.model.RoleHasFunction;
import org.example.apirest.repository.RoleHasFunctionRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleHasFunctionServiceImpl extends GeneralizedServiceImpl<RoleHasFunction, RoleHasFunctionDto, CreateRoleHasFunctionDto, RoleHasFunctionRepository> {
    public RoleHasFunctionServiceImpl(RoleHasFunctionRepository repository, DtoConverterGeneralizedImpl<RoleHasFunction,RoleHasFunctionDto,CreateRoleHasFunctionDto> dtoConverter) {
        super(repository, dtoConverter, RoleHasFunction.class, RoleHasFunctionDto.class);
    }
}
