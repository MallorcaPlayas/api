package org.example.apirest.dto.excursion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateExcursionDto extends BaseDto {
    private String description;
    private Date creationDate;
}
