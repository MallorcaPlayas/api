package org.example.apirest.dto.excursion;

import lombok.Data;
import org.example.apirest.dto.BaseDto;

import java.util.Date;

@Data
public class CreateExcursionDto extends BaseDto {
    private String description;
    private Date creationDate;
}
