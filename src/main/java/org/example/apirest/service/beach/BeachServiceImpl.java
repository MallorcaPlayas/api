package org.example.apirest.service.beach;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.model.Beach;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeachServiceImpl implements BeachService {

    @Override
    public List<BeachDto> findAll() {
        return List.of();
    }

    @Override
    public BeachDto findOne(Long id) {
        return null;
    }

    @Override
    public List<Beach> findAllNewest(int openSince) {
        return List.of();
    }

    @Override
    public BeachDto save(CreateBeachDto restaurant) {
        return null;
    }

    @Override
    public BeachDto update(Long id, CreateBeachDto restaurant) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
