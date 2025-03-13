package org.example.apirest.service.document;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.dto.document.DocumentDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final JpaRepository<Document, Long> repository;
    private final DtoConverter<Document,DocumentDto> dtoConverter;

    public List<DocumentDto> findAll(){
        return repository.findAll()
                .stream()
                .map(dtoConverter::entityToDto)
                .toList();
    }

    public DocumentDto findOne(Long id){
        return dtoConverter.entityToDto(repository.findById(id).orElse(null));
    }
}
