package org.example.apirest.dto.excursion;

import lombok.Data;

import java.util.Date;

@Data
public class CreateExcursionDto {
    private String description;
    private Date creationDate;
}
