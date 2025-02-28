package org.example.apirest.dto.document;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverter;
import org.example.apirest.model.Document;
import org.example.apirest.service.s3.S3Service;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class DocumentDtoCoverter implements DtoConverter<Document, DocumentDto> {

    private final ModelMapper modelMapper;
    private final S3Service s3Service;

    @Override
    public DocumentDto entityToDto(Document document) {
        DocumentDto dto = modelMapper.map(document, DocumentDto.class);
        String bucket = document.getBucket();
        String path = document.getPath();
        String url = s3Service.generateTeamporalUrl(bucket,path);
        dto.setUrl(url);
        return dto;
    }

    @Override
    public Document dtoToEntity(DocumentDto documentDto) {
        return modelMapper.map(documentDto, Document.class);
    }
}
