package org.example.apirest.controller;

import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.segment.CreateSegmentDto;
import org.example.apirest.dto.segment.SegmentDto;
import org.example.apirest.service.route.RouteServiceImpl;
import org.example.apirest.service.segment.SegmentServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/segments")
@CrossOrigin(origins = "*")
public class SegmentController extends GeneralizedController<SegmentDto, CreateSegmentDto> {
    public SegmentController(SegmentServiceImpl service) {
        super(service);
    }
}
