package org.example.apirest.controller;

import org.example.apirest.dto.complaint.ComplaintDto;
import org.example.apirest.dto.complaint.CreateComplaintDto;
import org.example.apirest.service.complaint.ComplaintServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(origins = "*")
public class ComplaintController extends GeneralizedControllerImpl<ComplaintDto, CreateComplaintDto> {
    public ComplaintController(ComplaintServiceImpl service) {
        super(service);
    }
}
