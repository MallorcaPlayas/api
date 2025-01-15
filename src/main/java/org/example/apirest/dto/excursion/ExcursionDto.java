package org.example.apirest.dto.excursion;

import lombok.Data;

import java.util.Date;

@Data
public class ExcursionDto{
    private Long id;
    private String description;
    private Date creationDate;
}
