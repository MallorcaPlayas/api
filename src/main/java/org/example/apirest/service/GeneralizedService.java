package org.example.apirest.service;

import java.util.List;

public interface GeneralizedService <Dto,CreateDto>{
    List<Dto> findAll();
    Dto findOne(Long id);
    Dto save(CreateDto dto);
    Dto update(Long id, CreateDto createDto);
    void delete(Long id);
}
