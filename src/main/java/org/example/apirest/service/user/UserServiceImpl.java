package org.example.apirest.service.user;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.repository.OrganizationRepository;
import org.example.apirest.repository.UserRepository;
import org.example.apirest.service.role.RoleServiceImpl;
import org.example.apirest.service.userHasRole.UserHasRoleServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("userServiceImpl") // Hay 2 clases que implementan UserDetailsService, por lo que necesitamos especificar cuál usar en la inyección de dependencias cuando se necesite UserDetailsService
//  UserServiceImpl manejar la lógica relacionada con los usuarios, incluyendo operaciones CRUD
//  y autenticación para Spring Security.
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final UserHasRoleServiceImpl userHasRoleService;

    public List<UserDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public UserDto findOne(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));
        return this.toDto(user);
    }

    public void delete(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));
        repository.delete(user);
    }

    public UserDto save(CreateUserDto createUserDto) {
        User entityToInsert = fromDto(createUserDto); // Convertir el CreateUserDto a User para guardarlo en la base de datos

//        if (createUserDto.getOrganization() != null) {
//            Organization org = organizationRepository.findById(createUserDto.getOrganization().getId())
//                    .orElseThrow(() -> new NotFoundException(Organization.class, createUserDto.getOrganization().getId()));
//            entityToInsert.setOrganization(org);
//        }

        if (createUserDto.getRoles() != null) {
            List<UserHasRole> roles = userHasRoleService.fromDtoList(createUserDto.getRoles());
            for (UserHasRole role : roles) {
                role.setUser(entityToInsert);
            }
            entityToInsert.setRoles(roles);
        }

        // Codificar la contraseña antes de guardarla en la base de datos
        entityToInsert.setPassword(passwordEncoder.encode(createUserDto.getPassword()));

        return toDto(repository.save(entityToInsert));
    }

    public UserDto update(Long id, CreateUserDto entity) {
        User old = repository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
        User newEntity = dtoConverter.convertToEntityFromCreateDto(entity, User.class);

        UtilsClass.updateFields(old, newEntity);

        if (entity.getOrganization() != null) {
            Organization org = organizationRepository.findById(entity.getOrganization().getId())
                    .orElseThrow(() -> new NotFoundException(Organization.class, entity.getOrganization().getId()));
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

    public Optional<UserDto> findByUserName(String username) {
        return repository.findByUserName(username)
                .map(user -> dtoConverter.convertDto(user, UserDto.class));
    }

    public Optional<UserDto> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(user -> dtoConverter.convertDto(user, UserDto.class));
    }


    // Implementación de UserDetailsService para Spring Security
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                Collections.emptyList()
        );
    }

    @Override
    public UserDto toDto(User user) {
        return mapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> toDtoList(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public User fromDto(CreateUserDto createUserDto) {
        return mapper.map(createUserDto, User.class);
    }

    @Override
    public List<User> fromDtoList(List<CreateUserDto> createUserDtos) {
        return createUserDtos.stream()
                .map(this::fromDto)
                .toList();
    }
}
