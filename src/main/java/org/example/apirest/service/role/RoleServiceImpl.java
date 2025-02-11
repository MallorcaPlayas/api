package org.example.apirest.service.role;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.role.CreateRoleDto;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.repository.RoleRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.service.function.FunctionServiceImpl;
import org.example.apirest.service.roleHasFunction.RoleHasFunctionServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements DtoConverter<Role,RoleDto,CreateRoleDto> {

    private final RoleRepository repository;
    private final ModelMapper mapper;
    private final RoleHasFunctionServiceImpl roleHasFunctionService;

    public List<RoleDto> findAll() {
        return this.toDtoList(repository.findAll());
    }


    public RoleDto findOne(Long id) {
        Role entity = repository.findById(id).orElseThrow(()-> new NotFoundException(Role.class,id));
        return toDto(entity);
    }


    public void delete(Long id) {
        Role entity = repository.findById(id).orElseThrow(()-> new NotFoundException(Role.class,id));
        repository.delete(entity);
    }

    public RoleDto save(CreateRoleDto entity) {
        Role entityToInsert = this.fromDto(entity);
        return this.toDto(repository.save(entityToInsert));

    }

//    public RoleDto update(Long id, CreateRoleDto entity) {
//        Role old = repository.findById(id).orElseThrow(() -> new NotFoundException(Role.class, id));
//        Role newEntity = this.fromDto(entity);
//
//        UtilsClass.updateFields(old, newEntity);
//
//        if (entity.getRoleHasFunctions() != null) {
//            old.getRoleHasFunctions().clear();
//            List<RoleHasFunction> functions = dtoFunction.convertToEntityListFromCreateDto(entity.getRoleHasFunctions(), RoleHasFunction.class);
//            for (RoleHasFunction function : functions) {
//                function.setRole(old);
//            }
//            old.getRoleHasFunctions().addAll(functions);
//        }
//
//        return dtoConverter.convertDto(repository.save(old), RoleDto.class);
//    }

    @Override
    public RoleDto toDto(Role role) {
        return mapper.map(role, RoleDto.class);
    }

    @Override
    public List<RoleDto> toDtoList(List<Role> roles) {
        return roles.stream().map(this::toDto).toList();
    }

    @Override
    public Role fromDto(CreateRoleDto createRoleDto) {
        Role role = mapper.map(createRoleDto, Role.class);
        List<RoleHasFunction> functions = roleHasFunctionService.fromDto((createRoleDto.getRoleHasFunctions()));
        for (RoleHasFunction function : functions) {
            function.setRole(role);
        }
        role.setRoleHasFunctions(functions);
        return role;
    }

    @Override
    public List<Role> fromDtoList(List<CreateRoleDto> createRoleDtos) {
        return createRoleDtos.stream().map(this::fromDto).toList();
    }
}
