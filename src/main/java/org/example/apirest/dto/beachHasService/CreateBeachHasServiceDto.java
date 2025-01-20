package org.example.apirest.dto.beachHasService;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseCreateDto;
import org.example.apirest.dto.typeBeach.CreateTypeBeachDto;
import org.example.apirest.model.Beach;
import org.example.apirest.model.ServiceBeach;

import java.time.LocalTime;
import java.util.List;

@Data
public class CreateBeachHasServiceDto{
    private Long beach_id;
    private Long serviceBeach_id;
    private LocalTime startTime;
    private LocalTime endTime;
}
