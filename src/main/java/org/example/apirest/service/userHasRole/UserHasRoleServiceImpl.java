package org.example.apirest.service.userHasRole;


import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.userHasRole.CreateUserHasRoleDto;
import org.example.apirest.dto.userHasRole.UserHasRoleDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.UserHasRole;
import org.example.apirest.repository.UserHasRoleRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserHasRoleServiceImpl implements DtoConverter<UserHasRole, UserHasRoleDto, CreateUserHasRoleDto> {
    
    private final UserHasRoleRepository repository;
    
    public List<UserHasRoleDto> findAll() {
        return this.toDtoList(repository.findAll());
    }
    
    public UserHasRoleDto findOne(Long id) {
        UserHasRole entity = repository.findById(id).orElseThrow(()-> new NotFoundException(UserHasRole.class,id));
        return this.toDto(entity);
    }
    
    public UserHasRoleDto save(UserHasRoleDto entity) {
        UserHasRole entityToInsert = dtoConverter.convertToEntityFromCreateDto(entity, UserHasRole.class);
        return this.toDto(repository.save(entityToInsert));
    }

   
    public UserHasRoleDto update(Long id, UserHasRoleDto createEntity) {
        UserHasRole oldEntity = repository.findById(id).orElseThrow(() -> new NotFoundException(UserHasRole.class, id));
        UserHasRole entityToInsert = dtoConverter.convertToEntityFromCreateDto(createEntity, UserHasRole.class);

        if (oldEntity == null) {
            return null;
        }

        UtilsClass.updateFields(oldEntity, entityToInsert);

        return this.toDto(repository.save(oldEntity), dtoClass);
    }

    
    public void delete(Long id) {
        UserHasRole entity = repository.findById(id).orElseThrow(()-> new NotFoundException(UserHasRole.class,id));
        repository.delete(entity);
    }

    @Override
    public UserHasRoleDto toDto(UserHasRole userHasRole) {
        return null;
    }

    @Override
    public List<UserHasRoleDto> toDtoList(List<UserHasRole> userHasRoles) {
        return List.of();
    }

    @Override
    public UserHasRole fromDto(CreateUserHasRoleDto createUserHasRoleDto) {
        return null;
    }

    @Override
    public List<UserHasRole> fromDtoList(List<CreateUserHasRoleDto> createUserHasRoleDtos) {
        return List.of();
    }
}
