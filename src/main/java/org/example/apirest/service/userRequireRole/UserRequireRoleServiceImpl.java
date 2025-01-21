package org.example.apirest.service.userRequireRole;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.userHasRole.CreateUserHasRoleDto;
import org.example.apirest.dto.userHasRole.UserHasRoleDto;
import org.example.apirest.dto.userRequireRole.CreateUserRequireRoleDto;
import org.example.apirest.dto.userRequireRole.UserRequireRoleDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Role;
import org.example.apirest.model.User;
import org.example.apirest.model.UserHasRole;
import org.example.apirest.model.UserRequireRole;
import org.example.apirest.repository.RoleRepository;
import org.example.apirest.repository.UserHasRoleRepository;
import org.example.apirest.repository.UserRepository;
import org.example.apirest.repository.UserRequireRoleRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRequireRoleServiceImpl extends GeneralizedServiceImpl<UserRequireRole, UserRequireRoleDto, CreateUserRequireRoleDto, UserRequireRoleRepository> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserRequireRoleServiceImpl(UserRequireRoleRepository repository, DtoConverterImpl<UserRequireRole, UserRequireRoleDto, CreateUserRequireRoleDto> dtoConverter, UserRepository userRepository, RoleRepository roleRepository) {
        super(repository, dtoConverter, UserRequireRole.class, UserRequireRoleDto.class);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserRequireRoleDto save(CreateUserRequireRoleDto entity) {
        UserRequireRole entityToInsert = dtoConverter.convertToEntityFromCreateDto(entity, UserRequireRole.class);
        User user = userRepository.findById(entity.getUser_id()).orElseThrow(()-> new NotFoundException(User.class,entity.getUser_id()));
        Role role = roleRepository.findById(entity.getRole_id()).orElseThrow(()-> new NotFoundException(Role.class,entity.getRole_id()));
        entityToInsert.setUser(user);
        entityToInsert.setRole(role);
        return dtoConverter.convertDto(repository.save(entityToInsert), UserRequireRoleDto.class);
    }

    @Override
    public UserRequireRoleDto update(Long id, CreateUserRequireRoleDto entity) {
        UserRequireRole old = repository.findById(id).orElseThrow(()-> new NotFoundException(UserRequireRole.class,id));
        UserRequireRole newEntity = dtoConverter.convertToEntityFromCreateDto(entity, UserRequireRole.class);

        if (entity == null) {
            return null;
        }

        UtilsClass.updateFields(old, newEntity);

        User user = userRepository.findById(entity.getUser_id()).orElseThrow(()-> new NotFoundException(User.class,entity.getUser_id()));
        Role role = roleRepository.findById(entity.getRole_id()).orElseThrow(()-> new NotFoundException(Role.class,entity.getRole_id()));
        old.setUser(user);
        old.setRole(role);

        return dtoConverter.convertDto(repository.save(old), UserRequireRoleDto.class);
    }
}
