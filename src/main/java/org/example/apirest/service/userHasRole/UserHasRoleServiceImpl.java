package org.example.apirest.service.userHasRole;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.dto.userHasRole.CreateUserHasRoleDto;
import org.example.apirest.dto.userHasRole.UserHasRoleDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Role;
import org.example.apirest.model.User;
import org.example.apirest.model.UserHasRole;
import org.example.apirest.repository.RoleRepository;
import org.example.apirest.repository.UserHasRoleRepository;
import org.example.apirest.repository.UserRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHasRoleServiceImpl extends GeneralizedServiceImpl<UserHasRole, UserHasRoleDto, CreateUserHasRoleDto, UserHasRoleRepository> {


    public UserHasRoleServiceImpl(UserHasRoleRepository repository, DtoConverterImpl<UserHasRole, UserHasRoleDto, CreateUserHasRoleDto> dtoConverter) {
        super(repository, dtoConverter, UserHasRole.class, UserHasRoleDto.class);
    }
}
