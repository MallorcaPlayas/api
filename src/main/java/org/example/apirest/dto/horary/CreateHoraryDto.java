package org.example.apirest.dto.horary;

import lombok.Data;

import java.util.Date;

@Data
public class CreateHoraryDto{
    private Date startTime;
    private Date endTime;
}
