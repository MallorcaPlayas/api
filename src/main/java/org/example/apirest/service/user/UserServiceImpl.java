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
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DtoConverterImpl<User, UserDto, CreateUserDto> serviceDtoConverter;

    @Override
    public List<UserDto> findAll() {
        return serviceDtoConverter.convertDtoList(userRepository.findAll(), UserDto.class);
    }

    @Override
    public UserDto findOne(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException(User.class,id));
        return serviceDtoConverter.convertDto(user, UserDto.class);
    }

    @Override
    public UserDto save(CreateUserDto user) {
        User userToInsert = serviceDtoConverter.convertToEntityFromCreateDto(user, User.class);
        return serviceDtoConverter.convertDto(userRepository.save(userToInsert), UserDto.class);
    }

    @Override
    public UserDto update(Long id, CreateUserDto user) {
        User oldUser = userRepository.findById(id).orElseThrow(()-> new NotFoundException(User.class,id));
        User userToInsert = serviceDtoConverter.convertToEntityFromCreateDto(user, User.class);

        if (user == null) {
            return null;
        }

        UtilsClass.updateFields(oldUser, userToInsert);

        return serviceDtoConverter.convertDto(userRepository.save(oldUser), UserDto.class);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException(User.class,id));

        userRepository.delete(user);
    }
}
