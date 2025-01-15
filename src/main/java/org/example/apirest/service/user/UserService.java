package org.example.apirest.service.user;

import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.dto.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findOne(Long id);
    UserDto save(CreateUserDto restaurant);
    UserDto update(Long id, CreateUserDto restaurant);
    void delete(Long id);
}
