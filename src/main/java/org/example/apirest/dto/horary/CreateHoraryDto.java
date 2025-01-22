package org.example.apirest.dto.horary;

import lombok.Data;

import java.time.LocalTime;

@Data
public class CreateHoraryDto{
    private LocalTime startTime;
    private LocalTime endTime;
}
