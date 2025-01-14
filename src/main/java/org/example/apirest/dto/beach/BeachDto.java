package org.example.apirest.dto.beach;

import lombok.Data;

import java.util.List;

@Data
public class BeachDto {
    private Long id;
    private String name;
    private String information;
    private List<ServiceDto>
}
