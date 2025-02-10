package org.example.apirest.service.role;

import org.example.apirest.dto.role.CreateRoleDto;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl {

    rotected final R repository;

    @Override
    public List<Dto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), dtoClass);
    }

    @Override
    public Dto findOne(Long id) {
        Entity entity = repository.findById(id).orElseThrow(()-> new NotFoundException(entityClass,id));
        return dtoConverter.convertDto(entity, dtoClass);
    }

    @Override
    public Dto save(CreateDto entity) {
        Entity entityToInsert = dtoConverter.convertToEntityFromCreateDto(entity, entityClass);
        return dtoConverter.convertDto(repository.save(entityToInsert), dtoClass);
    }

    @Override
    public Dto update(Long id, CreateDto createEntity) {
        Entity oldEntity = repository.findById(id).orElseThrow(() -> new NotFoundException(entityClass, id));
        Entity entityToInsert = dtoConverter.convertToEntityFromCreateDto(createEntity, entityClass);

        if (oldEntity == null) {
            return null;
        }

        UtilsClass.updateFields(oldEntity, entityToInsert);

        return dtoConverter.convertDto(repository.save(oldEntity), dtoClass);
    }

    @Override
    public void delete(Long id) {
        Entity entity = repository.findById(id).orElseThrow(()-> new NotFoundException(entityClass,id));
        repository.delete(entity);
    }


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

    public RoleDto update(Long id, CreateRoleDto entity) {
        Role old = repository.findById(id).orElseThrow(() -> new NotFoundException(Role.class, id));
        Role newEntity = dtoConverter.convertToEntityFromCreateDto(entity, Role.class);

        UtilsClass.updateFields(old, newEntity);

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
