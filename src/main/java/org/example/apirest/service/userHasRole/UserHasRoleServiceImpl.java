package org.example.apirest.service.userHasRole;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.dto.userHasRole.CreateUserHasRoleDto;
import org.example.apirest.dto.userHasRole.UserHasRoleDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Role;
import org.example.apirest.model.User;
import org.example.apirest.model.UserHasRole;
import org.example.apirest.repository.RoleRepository;
import org.example.apirest.repository.UserHasRoleRepository;
import org.example.apirest.repository.UserRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHasRoleServiceImpl extends GeneralizedServiceImpl<UserHasRole, UserHasRoleDto, CreateUserHasRoleDto, UserHasRoleRepository> {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserHasRoleServiceImpl(UserHasRoleRepository repository, DtoConverterImpl<UserHasRole, UserHasRoleDto, CreateUserHasRoleDto> dtoConverter, UserRepository userRepository, RoleRepository roleRepository) {
        super(repository, dtoConverter, UserHasRole.class, UserHasRoleDto.class);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserHasRoleDto save(CreateUserHasRoleDto entity) {
        UserHasRole entityToInsert = dtoConverter.convertToEntityFromCreateDto(entity, UserHasRole.class);
        User user = userRepository.findById(entity.getUserId()).orElseThrow(()-> new NotFoundException(User.class,entity.getUserId()));
        Role role = roleRepository.findById(entity.getRoleId()).orElseThrow(()-> new NotFoundException(Role.class,entity.getRoleId()));
        entityToInsert.setUser(user);
        entityToInsert.setRole(role);
        return dtoConverter.convertDto(repository.save(entityToInsert), UserHasRoleDto.class);
    }

    @Override
    public UserHasRoleDto update(Long id, CreateUserHasRoleDto entity) {
        UserHasRole old = repository.findById(id).orElseThrow(()-> new NotFoundException(UserHasRole.class,id));
        UserHasRole newEntity = dtoConverter.convertToEntityFromCreateDto(entity, UserHasRole.class);

        if (entity == null) {
            return null;
        }

        UtilsClass.updateFields(old, newEntity);

        User user = userRepository.findById(entity.getUserId()).orElseThrow(()-> new NotFoundException(User.class,entity.getUserId()));
        Role role = roleRepository.findById(entity.getRoleId()).orElseThrow(()-> new NotFoundException(Role.class,entity.getRoleId()));
        old.setUser(user);
        old.setRole(role);

        return dtoConverter.convertDto(repository.save(old), UserHasRoleDto.class);
    }
}
