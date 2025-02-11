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
import org.example.apirest.repository.UserRequireRoleRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRequireRoleServiceImpl implements DtoConverter<UserRequireRole, UserRequireRoleDto,CreateUserRequireRoleDto> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRequireRoleRepository repository;
    private final ModelMapper mapper;

    public List<UserRequireRoleDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public UserRequireRoleDto findOne(Long id) {
        UserRequireRole entity = repository.findById(id).orElseThrow(()-> new NotFoundException(UserRequireRole.class,id));
        return this.toDto(entity);
    }

    public void delete(Long id) {
        UserRequireRole entity = repository.findById(id).orElseThrow(()-> new NotFoundException(UserRequireRole.class,id));
        repository.delete(entity);
    }

    public UserRequireRoleDto save(CreateUserRequireRoleDto entity) {
        UserRequireRole entityToInsert = this.fromDto(entity);
        User user = userRepository.findById(entity.getUser_id()).orElseThrow(()-> new NotFoundException(User.class,entity.getUser_id()));
        Role role = roleRepository.findById(entity.getRole_id()).orElseThrow(()-> new NotFoundException(Role.class,entity.getRole_id()));
        entityToInsert.setUser(user);
        entityToInsert.setRole(role);
        return this.toDto(repository.save(entityToInsert));
    }

    public UserRequireRoleDto update(Long id, CreateUserRequireRoleDto entity) {
        UserRequireRole old = repository.findById(id).orElseThrow(()-> new NotFoundException(UserRequireRole.class,id));
        UserRequireRole newEntity = this.fromDto(entity);

        UtilsClass.updateFields(old, newEntity);

        User user = userRepository.findById(entity.getUser_id()).orElseThrow(()-> new NotFoundException(User.class,entity.getUser_id()));
        Role role = roleRepository.findById(entity.getRole_id()).orElseThrow(()-> new NotFoundException(Role.class,entity.getRole_id()));
        old.setUser(user);
        old.setRole(role);

        return this.toDto(repository.save(old));
    }

    @Override
    public UserRequireRoleDto toDto(UserRequireRole userRequireRole) {
        return mapper.map(userRequireRole,UserRequireRoleDto.class);
    }

    @Override
    public List<UserRequireRoleDto> toDtoList(List<UserRequireRole> userRequireRoles) {
        return userRequireRoles.stream().map(this::toDto).toList();
    }

    @Override
    public UserRequireRole fromDto(CreateUserRequireRoleDto createUserRequireRoleDto) {
        return mapper.map(createUserRequireRoleDto,UserRequireRole.class);
    }

    @Override
    public List<UserRequireRole> fromDtoList(List<CreateUserRequireRoleDto> createUserRequireRoleDtos) {
        return createUserRequireRoleDtos.stream().map(this::fromDto).toList();
    }
}
