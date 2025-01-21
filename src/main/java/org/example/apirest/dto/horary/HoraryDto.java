package org.example.apirest.dto.horary;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.apirest.dto.BaseDto;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class HoraryDto extends BaseDto {
    private Date startTime;
    private Date endTime;
}
