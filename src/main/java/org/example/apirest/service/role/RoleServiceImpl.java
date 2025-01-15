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

    private final RoleRepository roleRepository;
    private final DtoConverterImpl<Role, RoleDto, CreateRoleDto> roleDtoConverter;

    @Override
    public List<RoleDto> findAll() {
        return roleDtoConverter.convertDtoList(roleRepository.findAll(),RoleDto.class);
    }

    @Override
    public RoleDto findOne(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(()-> new NotFoundException(Role.class,id));
        return roleDtoConverter.convertDto(role,RoleDto.class);
    }

    @Override
    public RoleDto save(CreateRoleDto role) {
        Role roleToInsert = roleDtoConverter.convertToEntityFromCreateDto(role,Role.class);
        return roleDtoConverter.convertDto(roleRepository.save(roleToInsert),RoleDto.class);
    }

    @Override
    public RoleDto update(Long id, CreateRoleDto role) {
        Role oldRole = roleRepository.findById(id).orElseThrow(()-> new NotFoundException(Role.class,id));
        Role roleToInsert = roleDtoConverter.convertToEntityFromCreateDto(role,Role.class);

        UtilsClass.updateFields(oldRole, roleToInsert);

        return roleDtoConverter.convertDto(roleRepository.save(oldRole),RoleDto.class);
    }

    @Override
    public void delete(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(()-> new NotFoundException(Role.class,id));

        roleRepository.delete(role);
    }
}
