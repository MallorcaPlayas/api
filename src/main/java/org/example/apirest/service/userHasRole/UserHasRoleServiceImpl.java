package org.example.apirest.service.userHasRole;


import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.userHasRole.CreateUserHasRoleDto;
import org.example.apirest.dto.userHasRole.UserHasRoleDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.UserHasRole;
import org.example.apirest.repository.UserHasRoleRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserHasRoleServiceImpl implements DtoConverter<UserHasRole, UserHasRoleDto, CreateUserHasRoleDto> {
    
    private final UserHasRoleRepository repository;
    private final ModelMapper mapper;
    
    public List<UserHasRoleDto> findAll() {
        return this.toDtoList(repository.findAll());
    }
    
    public UserHasRoleDto findOne(Long id) {
        UserHasRole entity = repository.findById(id).orElseThrow(()-> new NotFoundException(UserHasRole.class,id));
        return this.toDto(entity);
    }
    
    public UserHasRoleDto save(CreateUserHasRoleDto entity) {
        UserHasRole entityToInsert = this.fromDto(entity);
        return this.toDto(repository.save(entityToInsert));
    }

   
    public UserHasRoleDto update(Long id, CreateUserHasRoleDto createEntity) {
        UserHasRole oldEntity = repository.findById(id).orElseThrow(() -> new NotFoundException(UserHasRole.class, id));
        UserHasRole entityToInsert = fromDto(createEntity);

        UtilsClass.updateFields(oldEntity, entityToInsert);

        return this.toDto(repository.save(oldEntity));
    }

    
    public void delete(Long id) {
        UserHasRole entity = repository.findById(id).orElseThrow(()-> new NotFoundException(UserHasRole.class,id));
        repository.delete(entity);
    }

    @Override
    public UserHasRoleDto toDto(UserHasRole userHasRole) {
        return mapper.map(userHasRole,UserHasRoleDto.class);
    }

    @Override
    public List<UserHasRoleDto> toDtoList(List<UserHasRole> userHasRoles) {
        return userHasRoles.stream().map(this::toDto).toList();
    }

    @Override
    public UserHasRole fromDto(CreateUserHasRoleDto createUserHasRoleDto) {
        return mapper.map(createUserHasRoleDto,UserHasRole.class);
    }

    @Override
    public List<UserHasRole> fromDtoList(List<CreateUserHasRoleDto> createUserHasRoleDtos) {
        return createUserHasRoleDtos.stream().map(this::fromDto).toList();
    }
}
