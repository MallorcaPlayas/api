package org.example.apirest.service.segment;

import org.example.apirest.dto.DtoConverterImpl;
import org.example.apirest.dto.route.CreateRouteDto;
import org.example.apirest.dto.route.RouteDto;
import org.example.apirest.dto.segment.CreateSegmentDto;
import org.example.apirest.dto.segment.SegmentDto;
import org.example.apirest.model.Route;
import org.example.apirest.model.Segment;
import org.example.apirest.repository.RouteRepository;
import org.example.apirest.repository.SegmentRepository;
import org.example.apirest.service.GeneralizedServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SegmentServiceImpl extends GeneralizedServiceImpl<Segment, SegmentDto, CreateSegmentDto, SegmentRepository> {
    public SegmentServiceImpl(SegmentRepository repository, DtoConverterImpl<Segment,SegmentDto,CreateSegmentDto> dtoConverter) {
        super(repository, dtoConverter, Segment.class, SegmentDto.class);
    }
}
