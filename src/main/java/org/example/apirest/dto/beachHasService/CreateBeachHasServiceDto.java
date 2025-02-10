package org.example.apirest.dto.beachHasService;

import lombok.Data;
import org.example.apirest.dto.service.ServiceBeachDto;
import java.time.LocalTime;

@Data
public class CreateBeachHasServiceDto{
    private String name;
    private Long beach_id;
    private ServiceBeachDto serviceBeach;
    private LocalTime startTime;
    private LocalTime endTime;
}
