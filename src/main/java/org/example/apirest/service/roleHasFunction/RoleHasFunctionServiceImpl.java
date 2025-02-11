package org.example.apirest.service.roleHasFunction;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.role_has_function.CreateRoleHasFunctionDto;
import org.example.apirest.dto.role_has_function.RoleHasFunctionDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.RoleHasFunction;
import org.example.apirest.repository.RoleHasFunctionRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleHasFunctionServiceImpl implements DtoConverter<RoleHasFunction, RoleHasFunctionDto, CreateRoleHasFunctionDto> {

    private final RoleHasFunctionRepository repository;
    private final ModelMapper mapper;

    public List<RoleHasFunctionDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public RoleHasFunctionDto findOne(Long id) {
        RoleHasFunction roleHasFunction = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(RoleHasFunction.class, id));
        return this.toDto(roleHasFunction);
    }

    public RoleHasFunctionDto save(CreateRoleHasFunctionDto createRoleHasFunctionDto) {
        RoleHasFunction roleHasFunction = fromDto(createRoleHasFunctionDto);
        RoleHasFunction savedRoleHasFunction = repository.save(roleHasFunction);
        return toDto(savedRoleHasFunction);
    }

    public RoleHasFunctionDto update(Long id, CreateRoleHasFunctionDto createRoleHasFunctionDto) {
        RoleHasFunction oldRoleHasFunction = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(RoleHasFunction.class, id));
        RoleHasFunction roleHasFunctionToUpdate = fromDto(createRoleHasFunctionDto);

        UtilsClass.updateFields(oldRoleHasFunction, roleHasFunctionToUpdate);

        RoleHasFunction savedRoleHasFunction = repository.save(oldRoleHasFunction);
        return toDto(savedRoleHasFunction);
    }

    public void delete(Long id) {
        RoleHasFunction roleHasFunction = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(RoleHasFunction.class, id));
        repository.delete(roleHasFunction);
    }

    @Override
    public RoleHasFunctionDto toDto(RoleHasFunction roleHasFunction) {
        return mapper.map(roleHasFunction, RoleHasFunctionDto.class);
    }

    @Override
    public List<RoleHasFunctionDto> toDtoList(List<RoleHasFunction> roleHasFunctions) {
        return roleHasFunctions.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public RoleHasFunction fromDto(CreateRoleHasFunctionDto createRoleHasFunctionDto) {
        return mapper.map(createRoleHasFunctionDto, RoleHasFunction.class);
    }

    @Override
    public List<RoleHasFunction> fromDtoList(List<CreateRoleHasFunctionDto> createRoleHasFunctionDtos) {
        return createRoleHasFunctionDtos.stream()
                .map(this::fromDto)
                .toList();
    }
}
