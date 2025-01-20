package org.example.apirest.service;

import org.example.apirest.dto.user.CreateUserDto;
import org.example.apirest.dto.user.UserDto;

import java.util.List;

public interface BaseService<Dto,CreateDto> {
    List<Dto> findAll();
    Dto findOne(Long id);
    Dto save(CreateDto dto);
    Dto update(Long id, CreateDto createDto);
    void delete(Long id);
}
