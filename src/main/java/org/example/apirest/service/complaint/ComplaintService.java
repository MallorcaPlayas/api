package org.example.apirest.service.complaint;

import org.example.apirest.dto.complaint.ComplaintDto;
import org.example.apirest.dto.complaint.CreateComplaintDto;

import java.util.List;

public interface ComplaintService {
    List<ComplaintDto> findAll();
    ComplaintDto findOne(Long id);
    ComplaintDto save(CreateComplaintDto complaint);
    ComplaintDto update(Long id, CreateComplaintDto complaint);
    void delete(Long id);
}
