package org.example.apirest.service.userHasRole;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.userHasRole.CreateUserHasRoleDto;
import org.example.apirest.dto.userHasRole.UserHasRoleDto;
import org.example.apirest.model.UserHasRole;
import org.example.apirest.repository.UserHasRoleRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserHasRoleServiceImpl extends GeneralizedServiceImpl<UserHasRole, UserHasRoleDto, CreateUserHasRoleDto, UserHasRoleRepository> {


    public UserHasRoleServiceImpl(UserHasRoleRepository repository, DtoConverterGeneralizedImpl<UserHasRole, UserHasRoleDto, CreateUserHasRoleDto> dtoConverter) {
        super(repository, dtoConverter, UserHasRole.class, UserHasRoleDto.class);
    }
}
