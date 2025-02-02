package org.example.apirest.controller;

import org.example.apirest.dto.complaint.ComplaintDto;
import org.example.apirest.dto.complaint.CreateComplaintDto;
import org.example.apirest.service.complaint.ComplaintServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/complaints")
@CrossOrigin(origins = "*")
public class ComplaintController extends GeneralizedController<ComplaintDto, CreateComplaintDto> {
    public ComplaintController(ComplaintServiceImpl service) {
        super(service);
    }
}
