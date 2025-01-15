package org.example.apirest.service.user;

import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.service.BaseService;

import java.util.List;

public interface UserService extends BaseService<UserDto,CreateUserDto> {
}
