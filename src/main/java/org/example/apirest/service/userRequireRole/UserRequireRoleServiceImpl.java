package org.example.apirest.service.userRequireRole;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.userRequireRole.CreateUserRequireRoleDto;
import org.example.apirest.dto.userRequireRole.UserRequireRoleDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Role;
import org.example.apirest.model.User;
import org.example.apirest.model.UserRequireRole;
import org.example.apirest.repository.RoleRepository;
import org.example.apirest.repository.UserRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRequireRoleServiceImpl {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

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

    public UserRequireRoleDto save(CreateUserRequireRoleDto entity) {
        UserRequireRole entityToInsert = dtoConverter.convertToEntityFromCreateDto(entity, UserRequireRole.class);
        User user = userRepository.findById(entity.getUser_id()).orElseThrow(()-> new NotFoundException(User.class,entity.getUser_id()));
        Role role = roleRepository.findById(entity.getRole_id()).orElseThrow(()-> new NotFoundException(Role.class,entity.getRole_id()));
        entityToInsert.setUser(user);
        entityToInsert.setRole(role);
        return dtoConverter.convertDto(repository.save(entityToInsert), UserRequireRoleDto.class);
    }

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
