package org.example.apirest.service;

import java.util.List;

public interface BeachService {
    List<PlayaDto> findAll();
    PlayaDto findOne(Long id);
    List<Playa> findAllNewest(int openSince);
    PlayaDto save(CreatePlayaDto restaurant);
    PlayaDto update(Long id, CreatePlayaDto restaurant);
    void delete(Long id);
}
