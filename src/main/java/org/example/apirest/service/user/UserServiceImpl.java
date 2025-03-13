package org.example.apirest.service.user;

import org.example.apirest.dto.DtoConverter;
import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.photo.PhotoDto;
import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.dto.userHasRole.CreateUserHasRoleDto;
import org.example.apirest.dto.userHasRole.UserHasRoleDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.*;
import org.example.apirest.repository.OrganizationRepository;
import org.example.apirest.repository.UserRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.Utils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("userServiceImpl")
// Hay 2 clases que implementan UserDetailsService, por lo que necesitamos especificar cuál usar en la inyección de dependencias cuando se necesite UserDetailsService
//  UserServiceImpl manejar la lógica relacionada con los usuarios, incluyendo operaciones CRUD
//  y autenticación para Spring Security.
public class UserServiceImpl extends GeneralizedServiceImpl<User, UserDto, CreateUserDto, UserRepository>
        implements UserDetailsService {

    private final OrganizationRepository organizationRepository;
    private final DtoConverterGeneralizedImpl<UserHasRole, UserHasRoleDto, CreateUserHasRoleDto> roleDto;
    private final DtoConverter<Photo, PhotoDto> photoDtoConverter;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository,
                           DtoConverterGeneralizedImpl<User, UserDto, CreateUserDto> dtoConverter,
                           OrganizationRepository organizationRepository,
                           DtoConverterGeneralizedImpl<UserHasRole, UserHasRoleDto, CreateUserHasRoleDto> roleDto,
                           PasswordEncoder passwordEncoder,
                           DtoConverter<Photo, PhotoDto> photoDtoConverter) {
        super(repository, dtoConverter, User.class, UserDto.class);

        this.organizationRepository = organizationRepository;
        this.roleDto = roleDto;
        this.passwordEncoder = passwordEncoder;
        this.photoDtoConverter = photoDtoConverter;
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = repository.findAll();
        return users.stream().map(user -> {
                List<Photo> photoList = user.getPhotos();

                List<PhotoDto> photoDtoList = new ArrayList<>();

                for (Photo photo : photoList) {
                    PhotoDto photoDto = photoDtoConverter.entityToDto(photo);
                    photoDtoList.add(photoDto);
                }


                UserDto userDto = dtoConverter.convertDto(user,UserDto.class);
                userDto.setPhoto(photoDtoList);
                return userDto;
                })
                .toList();
    }

    @Override
    public UserDto findOne(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException(User.class,id));
        List<Photo> photoList = user.getPhotos();

        List<PhotoDto> photoDtoList = new ArrayList<>();
        for (Photo photo1 : photoList) {
            PhotoDto photoDto = photoDtoConverter.entityToDto(photo1);
            photoDtoList.add(photoDto);
        }


        UserDto userDto = dtoConverter.convertDto(user,UserDto.class);
        userDto.setPhoto(photoDtoList);
        return userDto;
    }

    @Override
    public UserDto save(CreateUserDto createUserDto) {
        User entityToInsert = dtoConverter.convertToEntityFromCreateDto(createUserDto, User.class); // Convertir el CreateUserDto a User para guardarlo en la base de datos

        if (createUserDto.getOrganization() != null) {
            Organization org = organizationRepository.findById(createUserDto.getOrganization().getId())
                    .orElseThrow(() -> new NotFoundException(Organization.class, createUserDto.getOrganization().getId()));
            entityToInsert.setOrganization(org);
        }

        if (createUserDto.getRoles() != null) {
            List<UserHasRole> roles = roleDto.convertToEntityListFromCreateDto(createUserDto.getRoles(), UserHasRole.class);
            for (UserHasRole role : roles) {
                role.setUser(entityToInsert);
            }
            entityToInsert.setRoles(roles);
        }

        // Codificar la contraseña antes de guardarla en la base de datos
        entityToInsert.setPassword(passwordEncoder.encode(createUserDto.getPassword()));

        return dtoConverter.convertDto(repository.save(entityToInsert), UserDto.class);
    }

    @Override
    public UserDto update(Long id, CreateUserDto entity) {
        User old = repository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
        User newEntity = dtoConverter.convertToEntityFromCreateDto(entity, User.class);

        Utils.updateFields(old, newEntity);

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
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}
