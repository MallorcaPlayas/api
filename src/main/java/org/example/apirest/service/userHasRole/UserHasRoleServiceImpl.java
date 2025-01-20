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
import org.example.apirest.service.role.RoleService;
import org.example.apirest.service.user.UserService;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserHasRoleServiceImpl implements UserHasRoleService {

    private final UserHasRoleRepository repository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DtoConverterImpl<UserHasRole, UserHasRoleDto, CreateUserHasRoleDto> serviceDtoConverter;

    @Override
    public List<UserHasRoleDto> findAll() {
        return serviceDtoConverter.convertDtoList(repository.findAll(), UserHasRoleDto.class);
    }

    @Override
    public UserHasRoleDto findOne(Long id) {
        UserHasRole entity = repository.findById(id).orElseThrow(()-> new NotFoundException(UserHasRole.class,id));
        return serviceDtoConverter.convertDto(entity, UserHasRoleDto.class);
    }

    @Override
    public UserHasRoleDto save(CreateUserHasRoleDto entity) {
        UserHasRole entityToInsert = serviceDtoConverter.convertToEntityFromCreateDto(entity, UserHasRole.class);
        User user = userRepository.findById(entity.getUser_id()).orElseThrow(()-> new NotFoundException(User.class,entity.getUser_id()));
        Role role = roleRepository.findById(entity.getRole_id()).orElseThrow(()-> new NotFoundException(Role.class,entity.getRole_id()));
        entityToInsert.setUser(user);
        entityToInsert.setRole(role);
        return serviceDtoConverter.convertDto(repository.save(entityToInsert), UserHasRoleDto.class);
    }

    @Override
    public UserHasRoleDto update(Long id, CreateUserHasRoleDto entity) {
        UserHasRole old = repository.findById(id).orElseThrow(()-> new NotFoundException(UserHasRole.class,id));
        UserHasRole newEntity = serviceDtoConverter.convertToEntityFromCreateDto(entity, UserHasRole.class);

        if (entity == null) {
            return null;
        }

        UtilsClass.updateFields(old, newEntity);

        User user = userRepository.findById(entity.getUser_id()).orElseThrow(()-> new NotFoundException(User.class,entity.getUser_id()));
        Role role = roleRepository.findById(entity.getRole_id()).orElseThrow(()-> new NotFoundException(Role.class,entity.getRole_id()));
        old.setUser(user);
        old.setRole(role);

        return serviceDtoConverter.convertDto(repository.save(old), UserHasRoleDto.class);
    }

    @Override
    public void delete(Long id) {
        UserHasRole entity = repository.findById(id).orElseThrow(()-> new NotFoundException(UserHasRoleDto.class,id));
        repository.delete(entity);
    }
}
