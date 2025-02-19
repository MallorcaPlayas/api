package org.example.apirest.service.complaint;

import org.example.apirest.dto.DtoConverterGeneralizedImpl;
import org.example.apirest.dto.complaint.ComplaintDto;
import org.example.apirest.dto.complaint.CreateComplaintDto;
import org.example.apirest.model.Complaint;
import org.example.apirest.repository.ComplaintRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ComplaintServiceImpl extends GeneralizedServiceImpl<Complaint, ComplaintDto, CreateComplaintDto, ComplaintRepository> {
    public ComplaintServiceImpl(ComplaintRepository repository, DtoConverterGeneralizedImpl<Complaint,ComplaintDto,CreateComplaintDto> dtoConverter) {
        super(repository, dtoConverter, Complaint.class, ComplaintDto.class);
    }
}
