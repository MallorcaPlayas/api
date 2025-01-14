package org.example.apirest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeachServiceImpl implements BeachService {

    @Override
    public List<PlayaDto> findAll() {
        return List.of();
    }

    @Override
    public PlayaDto findOne(Long id) {
        return null;
    }

    @Override
    public List<Playa> findAllNewest(int openSince) {
        return List.of();
    }

    @Override
    public PlayaDto save(CreatePlayaDto restaurant) {
        return null;
    }

    @Override
    public PlayaDto update(Long id, CreatePlayaDto restaurant) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
