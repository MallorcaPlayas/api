package org.example.apirest.service.user;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.apirest.dto.user.UserDto;
import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.User;
import org.example.apirest.repository.UserRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService, DtoConverter<User, UserDto, CreateUserDto> {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public List<UserDto> findAll() {
        return this.toDtoList(userRepository.findAll());
    }

    public UserDto findOne(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));
        return this.toDto(user);
    }

    public UserDto save(CreateUserDto createUserDto) {
        User user = fromDto(createUserDto);
        User savedUser = userRepository.save(user);
        return toDto(savedUser);
    }

    public UserDto update(Long id, CreateUserDto createUserDto) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));
        User userToUpdate = fromDto(createUserDto);

        UtilsClass.updateFields(oldUser, userToUpdate);

        User savedUser = userRepository.save(oldUser);
        return toDto(savedUser);
    }

    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(User.class, id));
        userRepository.delete(user);
    }

    @Override
    public UserDto toDto(User user) {
        // You may use a mapper here if you have one, e.g., ModelMapper.
        return mapper.map(user,UserDto.class); // Adjust as needed
    }

    @Override
    public List<UserDto> toDtoList(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public User fromDto(CreateUserDto createUserDto) {
        return mapper.map(createUserDto,User.class); // Adjust as needed
    }

    @Override
    public List<User> fromDtoList(List<CreateUserDto> createUserDtos) {
        return createUserDtos.stream()
                .map(this::fromDto)
                .toList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}



