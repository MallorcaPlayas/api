package org.example.apirest.service.user;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.dto.userHasRole.CreateUserHasRoleDto;
import org.example.apirest.dto.userHasRole.UserHasRoleDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.repository.OrganizationRepository;
import org.example.apirest.repository.TypeBeachRepository;
import org.example.apirest.repository.UserRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends GeneralizedServiceImpl<User, UserDto, CreateUserDto, UserRepository> {
    private final OrganizationRepository organizationRepository;
    private final DtoConverterImpl<UserHasRole, UserHasRoleDto, CreateUserHasRoleDto> roleDto;
    public UserServiceImpl(UserRepository repository, DtoConverterImpl<User,UserDto,CreateUserDto> dtoConverter, OrganizationRepository organizationRepository, DtoConverterImpl<UserHasRole, UserHasRoleDto, CreateUserHasRoleDto> roleDto) {
        super(repository, dtoConverter, User.class, UserDto.class);
        this.organizationRepository = organizationRepository;
        this.roleDto = roleDto;
    }

    @Override
    public UserDto save(CreateUserDto entity) {
        User entityToInsert = dtoConverter.convertToEntityFromCreateDto(entity, User.class);

        if (entity.getOrganization() != null) {
            Organization org = organizationRepository.findById(entity.getOrganization().getId()).orElseThrow(() -> new NotFoundException(Organization.class, entity.getOrganization().getId()));;
            entityToInsert.setOrganization(org);
        }

        if (entity.getRoles() != null) {
            List<UserHasRole> roles = roleDto.convertToEntityListFromCreateDto(entity.getRoles(), UserHasRole.class);
            for (UserHasRole role : roles) {
                role.setUser(entityToInsert);
            }
            entityToInsert.setRoles(roles);
        }

        return dtoConverter.convertDto(repository.save(entityToInsert), UserDto.class);

    }

    @Override
    public UserDto update(Long id, CreateUserDto entity) {
        User old = repository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
        User newEntity = dtoConverter.convertToEntityFromCreateDto(entity, User.class);

        UtilsClass.updateFields(old, newEntity);

        if (entity.getOrganization() != null) {
            Organization org = organizationRepository.findById(entity.getOrganization().getId()).orElseThrow(() -> new NotFoundException(Organization.class, entity.getOrganization().getId()));;
            old.setOrganization(org);
        }

        if (entity.getRoles() != null) {
            old.getRoles().clear();
            List<UserHasRole> roles = roleDto.convertToEntityListFromCreateDto(entity.getRoles(), UserHasRole.class);
            for (UserHasRole role : roles) {
                role.setUser(old);
            }
            old.getRoles().addAll(roles);
        }

        return dtoConverter.convertDto(repository.save(old), UserDto.class);
    }
}
