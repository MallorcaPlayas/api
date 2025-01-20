package org.example.apirest.dto.beachHasService;

import lombok.Data;
import org.example.apirest.dto.beach.BeachDto;
import org.example.apirest.dto.service.ServiceBeachDto;
import org.example.apirest.dto.typeBeach.TypeBeachDto;
import org.example.apirest.model.Beach;
import org.example.apirest.model.ServiceBeach;

import java.time.LocalTime;
import java.util.List;

@Data
public class BeachHasServiceDto{
    private Long id;
    private BeachDto beach;
    private ServiceBeachDto serviceBeach;
    private LocalTime startTime;
    private LocalTime endTime;
}
