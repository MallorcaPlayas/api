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
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRequireRoleServiceImpl implements UserRequireRoleService {

    private final UserRequireRoleRepository repository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DtoConverterImpl<UserRequireRole, UserRequireRoleDto, CreateUserRequireRoleDto> serviceDtoConverter;

    @Override
    public List<UserRequireRoleDto> findAll() {
        return serviceDtoConverter.convertDtoList(repository.findAll(), UserRequireRoleDto.class);
    }

    @Override
    public UserRequireRoleDto findOne(Long id) {
        UserRequireRole entity = repository.findById(id).orElseThrow(()-> new NotFoundException(UserRequireRole.class,id));
        return serviceDtoConverter.convertDto(entity, UserRequireRoleDto.class);
    }

    @Override
    public UserRequireRoleDto save(CreateUserRequireRoleDto entity) {
        UserRequireRole entityToInsert = serviceDtoConverter.convertToEntityFromCreateDto(entity, UserRequireRole.class);
        User user = userRepository.findById(entity.getUser_id()).orElseThrow(()-> new NotFoundException(User.class,entity.getUser_id()));
        Role role = roleRepository.findById(entity.getRole_id()).orElseThrow(()-> new NotFoundException(Role.class,entity.getRole_id()));
        entityToInsert.setUser(user);
        entityToInsert.setRole(role);
        return serviceDtoConverter.convertDto(repository.save(entityToInsert), UserRequireRoleDto.class);
    }

    @Override
    public UserRequireRoleDto update(Long id, CreateUserRequireRoleDto entity) {
        UserRequireRole old = repository.findById(id).orElseThrow(()-> new NotFoundException(UserRequireRole.class,id));
        UserRequireRole newEntity = serviceDtoConverter.convertToEntityFromCreateDto(entity, UserRequireRole.class);

        if (entity == null) {
            return null;
        }

        UtilsClass.updateFields(old, newEntity);

        User user = userRepository.findById(entity.getUser_id()).orElseThrow(()-> new NotFoundException(User.class,entity.getUser_id()));
        Role role = roleRepository.findById(entity.getRole_id()).orElseThrow(()-> new NotFoundException(Role.class,entity.getRole_id()));
        old.setUser(user);
        old.setRole(role);

        return serviceDtoConverter.convertDto(repository.save(old), UserRequireRoleDto.class);
    }

    @Override
    public void delete(Long id) {
        UserRequireRole entity = repository.findById(id).orElseThrow(()-> new NotFoundException(UserRequireRole.class,id));
        repository.delete(entity);
    }
}
