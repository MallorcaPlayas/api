package org.example.apirest.service.user;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.TypeBeach;
import org.example.apirest.model.User;
import org.example.apirest.repository.TypeBeachRepository;
import org.example.apirest.repository.UserRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends GeneralizedServiceImpl<User, UserDto, CreateUserDto, UserRepository> {
    public UserServiceImpl(UserRepository repository, DtoConverterImpl<User,UserDto,CreateUserDto> dtoConverter) {
        super(repository, dtoConverter, User.class, UserDto.class);
    }
}
