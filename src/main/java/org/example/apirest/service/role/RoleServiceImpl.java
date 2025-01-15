package org.example.apirest.service.role;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.role.CreateRoleDto;
import org.example.apirest.dto.role.RoleDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Role;
import org.example.apirest.repository.RoleRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository repository;
    private final DtoConverterImpl<Role, RoleDto, CreateRoleDto> dtoConverter;

    @Override
    public List<RoleDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(),RoleDto.class);
    }

    @Override
    public RoleDto findOne(Long id) {
        Role role = repository.findById(id).orElseThrow(()-> new NotFoundException(Role.class,id));
        return dtoConverter.convertDto(role,RoleDto.class);
    }

    @Override
    public RoleDto save(CreateRoleDto role) {
        Role roleToInsert = dtoConverter.convertToEntityFromCreateDto(role,Role.class);
        return dtoConverter.convertDto(repository.save(roleToInsert),RoleDto.class);
    }

    @Override
    public RoleDto update(Long id, CreateRoleDto role) {
        Role oldRole = repository.findById(id).orElseThrow(()-> new NotFoundException(Role.class,id));
        Role roleToInsert = dtoConverter.convertToEntityFromCreateDto(role,Role.class);

        UtilsClass.updateFields(oldRole, roleToInsert);

        return dtoConverter.convertDto(repository.save(oldRole),RoleDto.class);
    }

    @Override
    public void delete(Long id) {
        Role role = repository.findById(id).orElseThrow(()-> new NotFoundException(Role.class,id));

        repository.delete(role);
    }
}
