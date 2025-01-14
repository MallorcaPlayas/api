package org.example.apirest.dto.service;

import lombok.Data;

import java.time.LocalTime;

@Data
public class CreateServiceDto {
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
}
