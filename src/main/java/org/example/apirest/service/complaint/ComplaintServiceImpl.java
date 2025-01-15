package org.example.apirest.service.complaint;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.complaint.ComplaintDto;
import org.example.apirest.dto.complaint.CreateComplaintDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Complaint;
import org.example.apirest.repository.ComplaintRepository;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository repository;
    private final DtoConverterImpl<Complaint, ComplaintDto, CreateComplaintDto> dtoConverter;

    @Override
    public List<ComplaintDto> findAll() {
        return dtoConverter.convertDtoList(repository.findAll(), ComplaintDto.class);
    }

    @Override
    public ComplaintDto findOne(Long id) {
        Complaint complaint = repository.findById(id).orElseThrow(() -> new NotFoundException(Complaint.class, id));
        return dtoConverter.convertDto(complaint, ComplaintDto.class);
    }

    @Override
    public ComplaintDto save(CreateComplaintDto complaint) {
        Complaint complaintToInsert = dtoConverter.convertToEntityFromCreateDto(complaint, Complaint.class);
        return dtoConverter.convertDto(repository.save(complaintToInsert), ComplaintDto.class);
    }

    @Override
    public ComplaintDto update(Long id, CreateComplaintDto complaint) {
        Complaint oldComplaint = repository.findById(id).orElseThrow(() -> new NotFoundException(Complaint.class, id));
        Complaint complaintToInsert = dtoConverter.convertToEntityFromCreateDto(complaint, Complaint.class);

        if (oldComplaint == null) {
            return null;
        }

        UtilsClass.updateFields(oldComplaint, complaintToInsert);

        return dtoConverter.convertDto(repository.save(oldComplaint), ComplaintDto.class);
    }

    @Override
    public void delete(Long id) {
        Complaint complaint = repository.findById(id).orElseThrow(() -> new NotFoundException(Complaint.class, id));
        repository.delete(complaint);
    }
}
