package org.example.apirest.dto.excursion;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;
import org.example.apirest.model.Excursion;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExcursionDto extends BaseDto {
    private String description;
    private Date creationDate;
    private Long userId;
}
