package org.example.apirest.dto.horary;

import lombok.Data;
import org.example.apirest.dto.BaseDto;

import java.util.Date;

@Data
public class HoraryDto{
    private Long id;
    private Date startTime;
    private Date endTime;
}
