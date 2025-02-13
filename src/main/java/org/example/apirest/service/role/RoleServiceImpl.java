package org.example.apirest.service.role;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.role.CreateRoleDto;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.dto.role_has_function.CreateRoleHasFunctionDto;
import org.example.apirest.dto.role_has_function.RoleHasFunctionDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.repository.RoleRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends GeneralizedServiceImpl<Role, RoleDto, CreateRoleDto, RoleRepository> {
    private final DtoConverterGeneralizedImpl<RoleHasFunction, RoleHasFunctionDto, CreateRoleHasFunctionDto> dtoFunction;
    public RoleServiceImpl(RoleRepository repository, DtoConverterGeneralizedImpl<Role,RoleDto,CreateRoleDto> dtoConverter, DtoConverterGeneralizedImpl<RoleHasFunction, RoleHasFunctionDto, CreateRoleHasFunctionDto> dtoFunction) {
        super(repository, dtoConverter, Role.class, RoleDto.class);
        this.dtoFunction = dtoFunction;
    }

    @Override
    public RoleDto save(CreateRoleDto entity) {
        Role entityToInsert = dtoConverter.convertToEntityFromCreateDto(entity, Role.class);

        if (entity.getRoleHasFunctions() != null) {
            List<RoleHasFunction> functions = dtoFunction.convertToEntityListFromCreateDto(entity.getRoleHasFunctions(), RoleHasFunction.class);
            for (RoleHasFunction function : functions) {
                function.setRole(entityToInsert);
            }
            entityToInsert.setRoleHasFunctions(functions);
        }

        return dtoConverter.convertDto(repository.save(entityToInsert), RoleDto.class);

    }

    @Override
    public RoleDto update(Long id, CreateRoleDto entity) {
        Role old = repository.findById(id).orElseThrow(() -> new NotFoundException(Role.class, id));
        Role newEntity = dtoConverter.convertToEntityFromCreateDto(entity, Role.class);

        Utils.updateFields(old, newEntity);

        if (entity.getRoleHasFunctions() != null) {
            old.getRoleHasFunctions().clear();
            List<RoleHasFunction> functions = dtoFunction.convertToEntityListFromCreateDto(entity.getRoleHasFunctions(), RoleHasFunction.class);
            for (RoleHasFunction function : functions) {
                function.setRole(old);
            }
            old.getRoleHasFunctions().addAll(functions);
        }

        return dtoConverter.convertDto(repository.save(old), RoleDto.class);
    }
}
