package org.example.apirest.service.complaint;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.comment.CommentDto;
import org.example.apirest.dto.comment.CreateCommentDto;
import org.example.apirest.dto.complaint.ComplaintDto;
import org.example.apirest.dto.complaint.CreateComplaintDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Comment;
import org.example.apirest.model.Complaint;
import org.example.apirest.repository.CommentRepository;
import org.example.apirest.repository.ComplaintRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl extends GeneralizedServiceImpl<Complaint, ComplaintDto, CreateComplaintDto, ComplaintRepository> {
    public ComplaintServiceImpl(ComplaintRepository repository, DtoConverterImpl<Complaint,ComplaintDto,CreateComplaintDto> dtoConverter) {
        super(repository, dtoConverter, Complaint.class, ComplaintDto.class);
    }
}
