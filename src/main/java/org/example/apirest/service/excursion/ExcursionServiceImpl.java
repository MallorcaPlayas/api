package org.example.apirest.service.excursion;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.complaint.ComplaintDto;
import org.example.apirest.dto.complaint.CreateComplaintDto;
import org.example.apirest.dto.excursion.ExcursionDto;
import org.example.apirest.dto.excursion.CreateExcursionDto;
import org.example.apirest.error.NotFoundException;
import org.example.apirest.model.Complaint;
import org.example.apirest.model.Excursion;
import org.example.apirest.repository.ComplaintRepository;
import org.example.apirest.repository.ExcursionRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.example.apirest.utils.UtilsClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcursionServiceImpl extends GeneralizedServiceImpl<Excursion, ExcursionDto, CreateExcursionDto, ExcursionRepository> {
    public ExcursionServiceImpl(ExcursionRepository repository, DtoConverterImpl<Excursion,ExcursionDto,CreateExcursionDto> dtoConverter) {
        super(repository, dtoConverter, Excursion.class, ExcursionDto.class);
    }
}
