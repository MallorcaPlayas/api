package org.example.apirest.service.userRequireRole;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterGeneralized;
import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.userRequireRole.CreateUserRequireRoleDto;
import org.example.apirest.dto.userRequireRole.UserRequireRoleDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Role;
import org.example.apirest.model.User;
import org.example.apirest.model.UserRequireRole;
import org.example.apirest.repository.DocumentRepository;
import org.example.apirest.repository.RoleRepository;
import org.example.apirest.repository.UserRepository;
import org.example.apirest.repository.UserRequireRoleRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.service.document.DocumentService;
import org.example.apirest.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRequireRoleServiceImpl{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DocumentService documentService;
    private final UserRequireRoleRepository repository;
    private final DtoConverterGeneralizedImpl<UserRequireRole, UserRequireRoleDto, CreateUserRequireRoleDto> dtoConverter;

    public List<UserRequireRoleDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), UserRequireRoleDto.class);
    }

    public UserRequireRoleDto findOne(Long id) {
        UserRequireRole entity = repository.findById(id).orElseThrow(()-> new NotFoundException(UserRequireRole.class,id));
        return dtoConverter.convertDto(entity, UserRequireRoleDto.class);
    }

    public void delete(Long id) {
        UserRequireRole entity = repository.findById(id).orElseThrow(()-> new NotFoundException(UserRequireRole.class,id));
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

        Utils.updateFields(old, newEntity);

        User user = userRepository.findById(entity.getUser_id()).orElseThrow(()-> new NotFoundException(User.class,entity.getUser_id()));
        Role role = roleRepository.findById(entity.getRole_id()).orElseThrow(()-> new NotFoundException(Role.class,entity.getRole_id()));
        old.setUser(user);
        old.setRole(role);

        return dtoConverter.convertDto(repository.save(old), UserRequireRoleDto.class);
    }
}
