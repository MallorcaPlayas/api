package org.example.apirest.service.complaint;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.complaint.ComplaintDto;
import org.example.apirest.dto.complaint.CreateComplaintDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Complaint;
import org.example.apirest.repository.ComplaintRepository;
import org.example.apirest.service.DtoConverter;
import org.example.apirest.utils.UtilsClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements DtoConverter<Complaint, ComplaintDto, CreateComplaintDto> {

    private final ComplaintRepository repository;
    private final ModelMapper mapper;

    public List<ComplaintDto> findAll() {
        return this.toDtoList(repository.findAll());
    }

    public ComplaintDto findOne(Long id) {
        Complaint complaint = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Complaint.class, id));
        return this.toDto(complaint);
    }

    public ComplaintDto save(CreateComplaintDto entity) {
        Complaint complaint = fromDto(entity);
        return toDto(repository.save(complaint));
    }

    public ComplaintDto update(Long id, CreateComplaintDto createEntity) {
        Complaint oldEntity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Complaint.class, id));
        Complaint entityToInsert = fromDto(createEntity);

        UtilsClass.updateFields(oldEntity, entityToInsert);

        return toDto(repository.save(oldEntity));
    }

    public void delete(Long id) {
        Complaint complaint = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Complaint.class, id));
        repository.delete(complaint);
    }

    @Override
    public ComplaintDto toDto(Complaint complaint) {
        return mapper.map(complaint, ComplaintDto.class);
    }

    @Override
    public List<ComplaintDto> toDtoList(List<Complaint> entities) {
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public Complaint fromDto(CreateComplaintDto createComplaintDto) {
        return mapper.map(createComplaintDto, Complaint.class);
    }

    @Override
    public List<Complaint> fromDtoList(List<CreateComplaintDto> createComplaintDtos) {
        return createComplaintDtos.stream().map(this::fromDto).toList();
    }
}
