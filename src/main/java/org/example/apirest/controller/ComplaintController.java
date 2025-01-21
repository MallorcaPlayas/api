package org.example.apirest.controller;

import lombok.RequiredArgsConstructor;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.beach.CreateBeachDto;
import org.example.apirest.dto.complaint.ComplaintDto;
import org.example.apirest.dto.complaint.CreateComplaintDto;
import org.example.apirest.service.beach.BeachServiceImpl;
import org.example.apirest.service.complaint.ComplaintServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(origins = "*")
public class ComplaintController extends GeneralizedController<ComplaintDto, CreateComplaintDto> {
    public ComplaintController(ComplaintServiceImpl service) {
        super(service);
    }
}
